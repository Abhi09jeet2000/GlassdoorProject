/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation.ui.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.glassdoor.intern.presentation.theme.InternTheme
import com.glassdoor.intern.utils.previewParameterProviderOf
import kotlinx.coroutines.delay

/**
 * DONE: Define how long the error message will be displayed
 */
private const val SHOW_ERROR_MESSAGE_DURATION_IS_MILLIS: Long = 2500L

@Composable
internal fun ErrorMessageComponent(
    errorMessage: String?,
    hideErrorMessageAction: () -> Unit,
    modifier: Modifier = Modifier,
) = Crossfade(
    modifier = modifier,
    targetState = errorMessage,
    label = "ErrorMessageComponent",
) { state ->
    if (!state.isNullOrEmpty()) {
        /**
         * DONE: Define the [background color](https://developer.android.com/jetpack/compose/modifiers#scope-safety), as well as [the color, style, and alignment](https://developer.android.com/jetpack/compose/text/style-text) of the error message
         */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(InternTheme.dimensions.normal),
            contentAlignment = Alignment.Center  // This centers content both vertically and horizontally
        ) {
            Text(
                modifier = Modifier
//                    .fillMaxWidth()
                    .padding(InternTheme.dimensions.normal),

                text = state,
                color = MaterialTheme.colorScheme.onError,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )

            LaunchedEffect(key1 = errorMessage) {
                delay(SHOW_ERROR_MESSAGE_DURATION_IS_MILLIS)

                /**
                 * DONE: Call an action that hides the error message
                 */
                hideErrorMessageAction()
            }
        }
    }
}

@Preview(showBackground=false)
@Composable
private fun ErrorMessageComponentPreview(
    @PreviewParameter(ErrorMessageComponentPreviewParameterProvider::class) errorMessage: String?
) = InternTheme {
    ErrorMessageComponent(
        errorMessage = errorMessage,
        hideErrorMessageAction = { },
    )
}

private class ErrorMessageComponentPreviewParameterProvider :
    PreviewParameterProvider<String?> by previewParameterProviderOf(null, "Error message")
