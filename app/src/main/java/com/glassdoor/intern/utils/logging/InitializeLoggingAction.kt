/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.utils.logging

import timber.log.Timber
import javax.inject.Inject

internal class InitializeLoggingAction @Inject constructor(
    private val timberTrees: Set<@JvmSuppressWildcards Timber.Tree>
) {

    operator fun invoke() {
        timberTrees.forEach(Timber::plant)
    }
}
