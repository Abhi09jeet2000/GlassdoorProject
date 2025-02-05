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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.glassdoor.intern.R
import com.glassdoor.intern.presentation.theme.InternTheme
import com.glassdoor.intern.utils.previewParameterProviderOf

private val progressIndicatorStrokeWidth: Dp = 3.dp

@Composable
internal fun TopBarComponent(
    isLoading: Boolean,
    progressClickAction: () -> Unit,
    modifier: Modifier = Modifier,
) = TopAppBar(
    modifier = modifier,
    title = {
        /**
         * TODO: Declare a [title](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary#Text(androidx.compose.ui.text.AnnotatedString,androidx.compose.ui.Modifier,androidx.compose.ui.graphics.Color,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.font.FontStyle,androidx.compose.ui.text.font.FontWeight,androidx.compose.ui.text.font.FontFamily,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.style.TextDecoration,androidx.compose.ui.text.style.TextAlign,androidx.compose.ui.unit.TextUnit,androidx.compose.ui.text.style.TextOverflow,kotlin.Boolean,kotlin.Int,kotlin.Int,kotlin.collections.Map,kotlin.Function1,androidx.compose.ui.text.TextStyle)) using the app name resource from strings
         */
    },
    actions = {
        ProgressIndicatorComponent(
            isLoading = isLoading,
            progressClickAction = progressClickAction,
        )
    },
)

@Composable
private fun ProgressIndicatorComponent(
    isLoading: Boolean,
    progressClickAction: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
) = IconButton(
    modifier = modifier,
    enabled = !isLoading,
    onClick = progressClickAction,
) {
    Crossfade(
        modifier = Modifier.padding(InternTheme.dimensions.normal),
        targetState = isLoading,
        label = "ProgressIndicatorComponent",
    ) { isLoadingState ->
        if (isLoadingState) {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                color = color,
                strokeWidth = progressIndicatorStrokeWidth,
            )
        } else {
            Icon(
                modifier = Modifier.fillMaxSize(),
                contentDescription = stringResource(R.string.accessibility_navigation_refresh_screen_content),
                imageVector = Icons.Default.Refresh,
                tint = color,
            )
        }
    }
}

@Preview
@Composable
private fun TopBarComponentPreview(
    @PreviewParameter(TopBarComponentPreviewParameterProvider::class) isLoading: Boolean
) = InternTheme {
    /**
     * TODO: Define a component and use the state to preview it
     */
}

private class TopBarComponentPreviewParameterProvider :
    PreviewParameterProvider<Boolean> by previewParameterProviderOf(
        TODO("Define all possible states for preview purposes")
    )
