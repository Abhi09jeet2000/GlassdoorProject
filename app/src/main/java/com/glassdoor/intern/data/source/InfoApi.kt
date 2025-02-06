/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.data.source

import com.glassdoor.intern.BuildConfig
import com.glassdoor.intern.data.model.InfoDto
import retrofit2.http.GET

internal interface InfoApi {

    /**
     * DONE: Apply the [get annotation](https://square.github.io/retrofit) and specify the correct endpoint from `BuildConfig`
     */
    @GET(BuildConfig.ENDPOINT_GET_INFO)
    suspend fun getInfo(): InfoDto
}
