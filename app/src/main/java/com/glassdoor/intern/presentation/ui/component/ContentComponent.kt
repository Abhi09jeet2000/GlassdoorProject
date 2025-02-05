/*
 * Copyright (c) 2025, Glassdoor Inc.
 *
 * Licensed under the Glassdoor Inc Hiring Assessment License.
 * You may not use this file except in compliance with the License.
 * You must obtain explicit permission from Glassdoor Inc before sharing or distributing this file.
 * Mention Glassdoor Inc as the source if you use this code in any way.
 */

package com.glassdoor.intern.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.glassdoor.intern.presentation.model.HeaderUiModel
import com.glassdoor.intern.presentation.model.ItemUiModel
import com.glassdoor.intern.presentation.theme.InternTheme
import com.glassdoor.intern.utils.previewParameterProviderOf

private val headerBorderStrokeWidth: Dp = 3.dp
private val imageSize: Dp = 120.dp

@Composable
internal fun ContentComponent(
    header: HeaderUiModel,
    items: List<ItemUiModel>,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    HeaderComponent(
        modifier = Modifier
            .padding(horizontal = InternTheme.dimensions.normal)
            .padding(top = InternTheme.dimensions.normal),
        header = header,
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1F),
        contentPadding = PaddingValues(InternTheme.dimensions.double),
        verticalArrangement = Arrangement.spacedBy(InternTheme.dimensions.double),
    ) {
        /**
         * TODO: Specify the [item key](https://developer.android.com/jetpack/compose/lists#item-keys) and [content type](https://developer.android.com/jetpack/compose/lists#content-type)
         */
        items(
            items = items,
            itemContent = { item -> ItemComponent(item) },
        )
    }
}

@Composable
private fun HeaderComponent(
    header: HeaderUiModel,
    modifier: Modifier = Modifier,
) = AnimatedVisibility(
    modifier = modifier,
    enter = fadeIn(),
    exit = fadeOut(),
    label = "HeaderComponent",
    visible = !header.isEmpty,
) {
    Card(
        border = BorderStroke(
            width = headerBorderStrokeWidth,
            color = MaterialTheme.colorScheme.primary
        )
    ) {
        with(header) {
            /**
             * TODO: [Declare the UI](https://developer.android.com/codelabs/jetpack-compose-basics#5) based on the UI model structure
             */
        }
    }
}

@Composable
private fun ItemComponent(item: ItemUiModel) = Card {
    with(item) {
        Row(
            modifier = Modifier.padding(InternTheme.dimensions.double),
            horizontalArrangement = Arrangement.spacedBy(InternTheme.dimensions.double),
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = title,
                style = MaterialTheme.typography.titleSmall,
            )

            Text(
                text = timestamp,
                style = MaterialTheme.typography.labelSmall,
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = InternTheme.dimensions.double)
                .padding(horizontal = InternTheme.dimensions.double),
            horizontalArrangement = Arrangement.spacedBy(InternTheme.dimensions.double),
        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = description,
                style = MaterialTheme.typography.bodySmall,
            )

            AsyncImage(
                modifier = Modifier
                    .align(Alignment.Top)
                    .size(imageSize)
                    .clip(CardDefaults.shape),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                error = rememberVectorPainter(Icons.Default.Warning),
                model = TODO("[Request an image download](https://github.com/coil-kt/coil#requests)"),
            )
        }
    }
}

@Preview
@Composable
private fun ContentComponentPreview(
    @PreviewParameter(ContentComponentPreviewParameterProvider::class)
    headerAndItems: HeaderAndItems
) = InternTheme {
    ContentComponent(
        header = headerAndItems.first,
        items = headerAndItems.second,
    )
}


@Preview
@Composable
private fun HeaderComponentPreview(
    @PreviewParameter(HeaderComponentPreviewParameterProvider::class) header: HeaderUiModel
) = InternTheme {
    HeaderComponent(header = header)
}


@Preview
@Composable
private fun ItemComponentPreview(
    @PreviewParameter(ItemComponentPreviewParameterProvider::class) item: ItemUiModel
) = InternTheme {
    ItemComponent(item)
}

private typealias HeaderAndItems = Pair<HeaderUiModel, List<ItemUiModel>>

private class ContentComponentPreviewParameterProvider :
    PreviewParameterProvider<HeaderAndItems> by previewParameterProviderOf(
        TODO("Define UI models for preview purposes")
    )

private class HeaderComponentPreviewParameterProvider :
    PreviewParameterProvider<HeaderUiModel> by previewParameterProviderOf(
        TODO("Define UI models for preview purposes")
    )

private class ItemComponentPreviewParameterProvider :
    PreviewParameterProvider<ItemUiModel> by previewParameterProviderOf(
        ItemUiModel(
            title = "Item Title 0",
            description = "Item Description 0",
            imageUrl = null,
            timestamp = "10:00",
        ),
    )
