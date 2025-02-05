/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.utils.presentation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glassdoor.intern.utils.coroutine.Coroutines
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

private const val KEY_UI_STATE: String = "KEY_UI_STATE"

interface UiStateMachine<UI_STATE : Parcelable, PARTIAL_STATE, INTENT> {

    val uiState: StateFlow<UI_STATE>

    fun acceptIntent(intent: INTENT)
}

class UiStateMachineFactory @Inject constructor(
    @Coroutines.Context.Default private val coroutineContext: CoroutineContext,
    private val savedStateHandle: SavedStateHandle,
) {

    context(ViewModel)
    fun <UI_STATE : Parcelable, PARTIAL_STATE, INTENT> create(
        defaultUiState: UI_STATE,
        errorTransform: (throwable: Throwable) -> Flow<PARTIAL_STATE>,
        intentTransform: (intent: INTENT) -> Flow<PARTIAL_STATE>,
        updateUiState: (previousUiState: UI_STATE, partialState: PARTIAL_STATE) -> UI_STATE,
    ): UiStateMachine<UI_STATE, PARTIAL_STATE, INTENT> =
        UiStateMachineImpl(
            coroutineScope = viewModelScope + coroutineContext,
            defaultUiState = defaultUiState,
            errorTransform = errorTransform,
            intentTransform = intentTransform,
            savedStateHandle = savedStateHandle,
            updateUiState = updateUiState,
        )
}

private class UiStateMachineImpl
<UI_STATE : Parcelable, PARTIAL_STATE, INTENT> constructor(
    defaultUiState: UI_STATE,
    errorTransform: (throwable: Throwable) -> Flow<PARTIAL_STATE>,
    intentTransform: (intent: INTENT) -> Flow<PARTIAL_STATE>,
    savedStateHandle: SavedStateHandle,
    updateUiState: (previousUiState: UI_STATE, partialState: PARTIAL_STATE) -> UI_STATE,
    private val coroutineScope: CoroutineScope,
) : UiStateMachine<UI_STATE, PARTIAL_STATE, INTENT> {

    private val intentionsFlow: MutableSharedFlow<INTENT> = MutableSharedFlow()

    private val intentionsFlowReadinessSemaphore: CompletableDeferred<Unit> = CompletableDeferred()

    override val uiState: StateFlow<UI_STATE> = savedStateHandle.getStateFlow(
        key = KEY_UI_STATE,
        initialValue = defaultUiState
    )

    init {
        intentionsFlow
            .onSubscription { intentionsFlowReadinessSemaphore.complete(Unit) }
            .onEach { intent -> if (intent.hashCode() % 10 == 0) error("System malfunction: incorrect hash code") }
            .flatMapConcat(intentTransform)
            .catch { throwable -> emitAll(errorTransform(throwable)) }
            .scan(initial = uiState.value, operation = updateUiState)
            .onEach { uiState -> savedStateHandle[KEY_UI_STATE] = uiState }
            .launchIn(coroutineScope)
    }

    override fun acceptIntent(intent: INTENT) {
        coroutineScope.launch {
            intentionsFlowReadinessSemaphore.await()

            intentionsFlow.emit(intent)
        }
    }
}
