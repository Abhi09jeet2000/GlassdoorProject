/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.glassdoor.intern.presentation.model.HeaderUiModel
import com.glassdoor.intern.presentation.model.ItemUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
internal data class MainUiState(
    val errorMessage: String?,
    val header: HeaderUiModel,
    val isLoading: Boolean,
    val items: List<ItemUiModel>,
) : Parcelable {

    sealed interface PartialState {

        data object HideLoadingState : PartialState

        data object ShowLoadingState : PartialState

        data class UpdateErrorMessageState(val errorMessage: String?) : PartialState

        data class UpdateHeaderState(val header: HeaderUiModel) : PartialState

        data class UpdateItemsState(val items: List<ItemUiModel>) : PartialState
    }
}
