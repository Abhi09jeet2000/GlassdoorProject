/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.data.di

import com.glassdoor.intern.BuildConfig
import com.glassdoor.intern.data.network.TokenInterceptor
import com.glassdoor.intern.data.repository.InfoRepositoryImpl
import com.glassdoor.intern.data.source.InfoApi
import com.glassdoor.intern.domain.repository.InfoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
internal object DataModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor,
        interceptors: Set<@JvmSuppressWildcards Interceptor>,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .apply { interceptors.forEach(::addInterceptor) }
            .build()

    @ViewModelScoped
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_SERVER_URL)
            .build()

    @Provides
    fun provideInfoApi(retrofit: Retrofit): InfoApi =
        retrofit.create(InfoApi::class.java)

    @Module
    @InstallIn(ViewModelComponent::class)
    interface BindsModule {

        @ViewModelScoped
        @Binds
        fun bindInfoRepository(
            impl: InfoRepositoryImpl
        ): InfoRepository

        @IntoSet
        @Binds
        fun bindHttpHeadersInterceptor(
            httpHeadersInterceptor: TokenInterceptor
        ): Interceptor
    }
}
