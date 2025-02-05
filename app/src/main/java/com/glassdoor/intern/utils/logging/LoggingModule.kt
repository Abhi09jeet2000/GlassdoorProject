/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.utils.logging

import dagger.Provides
import dagger.multibindings.IntoSet
import timber.log.Timber

/**
 * TODO: [Annotate the DI module](https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules) with correct definitions and [component](https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules)
 */
internal object LoggingModule {

    @IntoSet
    @Provides
    fun provideTimberTree(): Timber.Tree =
        Timber.DebugTree()
}
