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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.glassdoor.intern.R

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
         * DONE : Specify the [item key](https://developer.android.com/jetpack/compose/lists#item-keys) and [content type](https://developer.android.com/jetpack/compose/lists#content-type)
         */
        items(
            items = items,
            key = {item->item.key},
            contentType = { item -> "item" },
            itemContent = { item -> ItemComponent(item) },
        )
    }
}

@Composable
private fun CollapsibleText(
    text:String
){
    var expanded by remember { mutableStateOf(false) }
    var maxLines = integerResource(id = R.integer.max_lines)

    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        color = MaterialTheme.colorScheme.secondary,
        maxLines = if (expanded) Int.MAX_VALUE else maxLines,
        overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis,
    )
    Text(
        text = if (expanded) "Show less" else "Read more",
        style = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .padding(top = InternTheme.dimensions.normal)
            .clickable { expanded = !expanded }
    )
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
             * DONE: [Declare the UI](https://developer.android.com/codelabs/jetpack-compose-basics#5) based on the UI model structure
             */
            Column(modifier = Modifier.padding(InternTheme.dimensions.normal)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Spacer(modifier = Modifier.height(InternTheme.dimensions.normal))
                CollapsibleText(text=description)
                Spacer(modifier = Modifier.height(InternTheme.dimensions.normal))
                Text(
                    text = timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ItemComponent(item: ItemUiModel) = Card(
    border = BorderStroke(
        width = 1.dp,
        color = MaterialTheme.colorScheme.secondary
    )
) {
    with(item) {
        Row(
            modifier = Modifier.padding(InternTheme.dimensions.double),
            horizontalArrangement = Arrangement.spacedBy(InternTheme.dimensions.double),

        ) {
            Text(
                modifier = Modifier.weight(1F),
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
            )

            Text(
                text = timestamp,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.secondary,
            )

            AsyncImage(
                modifier = Modifier
                    .align(Alignment.Top)
                    .size(imageSize)
                    .clip(CardDefaults.shape),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                error = rememberVectorPainter(Icons.Default.Warning),
//                model = DONE("[Request an image download](https://github.com/coil-kt/coil#requests)"),
                model = imageUrl?:""
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
        //DONE("Define UI models for preview purposes")
        Pair(
            HeaderUiModel(
                title = "Content Header",
                description = "This is a content header preview.",
                timestamp = "Nov 10, 2021 02:02",
                items = listOf(
                    ItemUiModel(
                        title = "Item Title 0",
                        description = "Item Description 0",
                        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Capricornus_IAU.svg/1204px-Capricornus_IAU.svg.png",
                        timestamp = "10:00"
                    ),
                    ItemUiModel(
                        title = "Item Title 1",
                        description = "Item Description 1",
                        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Capricornus_IAU.svg/1204px-Capricornus_IAU.svg.png",
                        timestamp = "11:00"
                    )
                )
            ),
            listOf(
                ItemUiModel(
                    title = "Item Title 0",
                    description = "Item Description 0",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Capricornus_IAU.svg/1204px-Capricornus_IAU.svg",
                    timestamp = "10:00"
                ),
                ItemUiModel(
                    title = "Item Title 1",
                    description = "Item Description 1",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Capricornus_IAU.svg/1204px-Capricornus_IAU.svg",
                    timestamp = "10:00"
                )
            )
        )
    )


private class HeaderComponentPreviewParameterProvider :
    PreviewParameterProvider<HeaderUiModel> by previewParameterProviderOf(
//        DONE("Define UI models for preview purposes")
        HeaderUiModel(
            title = "Header Preview Title",
            description = "This is a header preview description.",
            timestamp = "Jan 10, 2025 10:00",
            items = listOf(
                ItemUiModel(
                    title = "Item Title 0",
                    description = "Item Description 0",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Capricornus_IAU.svg/1204px-Capricornus_IAU.svg.png",
                    timestamp = "10:00"
                )
            )
        )
    )

private class ItemComponentPreviewParameterProvider :
    PreviewParameterProvider<ItemUiModel> by previewParameterProviderOf(
        ItemUiModel(
            title = "Item Title 0",
            description = "Item Description 0",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Capricornus_IAU.svg/1204px-Capricornus_IAU.svg.png",
            timestamp = "10:00",
        ),
    )
