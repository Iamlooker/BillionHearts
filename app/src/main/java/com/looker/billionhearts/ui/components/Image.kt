package com.looker.billionhearts.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun ShoeImage(
    @DrawableRes
    imageDrawable: Int,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillWidth,
    contentDescription: String? = null,
) {
    AsyncImage(
        contentScale = contentScale,
        model = imageDrawable,
        contentDescription = contentDescription,
        modifier = Modifier
            .then(modifier),
    )
}

@Composable
fun ShoeImage(
    @DrawableRes
    imageDrawable: Int,
    key: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillWidth,
    contentDescription: String? = null,
) {
    AsyncImage(
        contentScale = contentScale,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageDrawable)
            .crossfade(true)
            .placeholderMemoryCacheKey(key)
            .memoryCacheKey(key)
            .build(),
        contentDescription = contentDescription,
        modifier = Modifier
            .then(modifier),
    )
}
