/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation.mapper

import com.glassdoor.intern.domain.model.ItemInfo
import com.glassdoor.intern.presentation.model.ItemUiModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

private const val TIME_FORMAT_PATTERN: String = "HH:mm"

internal class ItemUiModelMapper @Inject constructor() {

    private val timeFormatter: DateTimeFormatter = DateTimeFormatter
        .ofPattern(TIME_FORMAT_PATTERN)
        .withZone(ZoneId.systemDefault())

    fun toUiModel(itemInfo: ItemInfo): ItemUiModel = with(itemInfo) {
        ItemUiModel(
            title = title,
            description = description,
            imageUrl = imageUrl,
            timestamp = Instant.ofEpochSecond(timestampInSeconds).let(timeFormatter::format),
        )
    }
}
