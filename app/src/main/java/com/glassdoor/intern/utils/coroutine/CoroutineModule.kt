/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.utils.coroutine

import dagger.Provides
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * TODO: [Annotate the DI module](https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules) with correct definitions and [component](https://developer.android.com/training/dependency-injection/hilt-android#hilt-modules)
 */
internal object CoroutineModule {

    @Singleton
    @Provides
    fun provideCoroutineExceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable, "CoroutineExceptionHandler")
        }

    /**
     * TODO: Annotate dependency with the correct [qualifier label](https://developer.android.com/training/dependency-injection/hilt-android#multiple-bindings)
     */
    @Singleton
    @Provides
    fun provideCoroutineContextDefault(
        coroutineContextFactory: CoroutineContextFactory
    ): CoroutineContext =
        coroutineContextFactory.create(Dispatchers.Default)
}
