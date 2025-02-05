/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

fun <T> previewParameterProviderOf(vararg values: T): PreviewParameterProvider<T> =
    object : PreviewParameterProvider<T> {
        override val values: Sequence<T> = values.asSequence()
    }
