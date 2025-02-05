/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern

import android.app.Application
import com.glassdoor.intern.utils.logging.InitializeLoggingAction
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
internal class InternApp : Application() {

    @Inject
    fun initializeLogging(initializeLoggingAction: InitializeLoggingAction) {
        initializeLoggingAction()
    }
}
