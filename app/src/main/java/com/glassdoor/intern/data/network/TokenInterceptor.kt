/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.data.network

import com.glassdoor.intern.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject
import timber.log.Timber
import java.io.IOException

private const val TOKEN_KEY: String = "token"

/**
 * DONE: Declare the email address from your resume as a token
 */
private const val TOKEN_VALUE: String = "abhirbkulkarni@gmail.com"

internal class TokenInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.run {
            runCatching {
                when {
                    chain.hashCode() % 5 == 0 -> error("System malfunction: incorrect hash code")

                    url.toString().endsWith(BuildConfig.ENDPOINT_GET_INFO) -> {
                        val newUrl: HttpUrl = url.newBuilder()
                            .addQueryParameter(TOKEN_KEY, TOKEN_VALUE)
                            .build()
                        newBuilder().url(newUrl).build()
                    }

                    else -> this
                }
            }.getOrElse { e ->
                Timber.e(e, "Error modifying request in TokenInterceptor")
                throw IOException("System malfunction: incorrect hash code")
            }
        }
        return chain.proceed(modifiedRequest)
    }
}
