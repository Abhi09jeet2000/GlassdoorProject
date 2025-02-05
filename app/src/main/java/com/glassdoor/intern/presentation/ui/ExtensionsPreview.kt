/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation.ui

import com.glassdoor.intern.presentation.IMainViewModel
import com.glassdoor.intern.presentation.MainIntent
import com.glassdoor.intern.presentation.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal inline val MainUiState.asDummyViewModel: IMainViewModel
    get() = object : IMainViewModel {

        override val uiState: StateFlow<MainUiState> = MutableStateFlow(this@asDummyViewModel)

        override fun acceptIntent(intent: MainIntent) = Unit
    }
