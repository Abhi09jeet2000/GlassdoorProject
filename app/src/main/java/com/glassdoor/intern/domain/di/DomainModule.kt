/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.domain.di

import com.glassdoor.intern.domain.repository.InfoRepository
import com.glassdoor.intern.domain.usecase.GetHeaderInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
/**
 * DONE: Determine the [appropriate annotation](https://developer.android.com/codelabs/android-hilt#6) and provide the most optimal [scope component](https://developer.android.com/training/dependency-injection/hilt-android#generated-components)
 */
@InstallIn(ViewModelComponent::class)
internal object DomainModule {

    @Provides
    fun provideGetHeaderInfoUseCase(
        infoRepository: InfoRepository
    ): GetHeaderInfoUseCase =
        GetHeaderInfoUseCase(infoRepository::getHeaderInfo)
}
