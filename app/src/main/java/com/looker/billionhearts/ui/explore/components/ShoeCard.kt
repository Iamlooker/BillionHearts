package com.looker.billionhearts.ui.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.looker.billionhearts.data.Shoe
import com.looker.billionhearts.data.mockShoeData
import com.looker.billionhearts.ui.components.ShoeImage
import com.looker.billionhearts.ui.components.formatPrice
import com.looker.billionhearts.ui.theme.BillionHeartsTheme

@Composable
fun ShoeCard(
    shoe: Shoe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundModifier: Modifier = Modifier,
    image: @Composable (Int) -> Unit,
) {
    val defaultVariant = remember(shoe) { shoe.variants.first() }
    val shape = MaterialTheme.shapes.large
    Box(
        modifier = modifier
            .size(width = 272.dp, height = 288.dp)
            .clip(shape)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .size(width = 224.dp, height = 288.dp)
                .clip(MaterialTheme.shapes.large)
                .background(defaultVariant.color)
                .then(backgroundModifier),
        )
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = shoe.name,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = formatPrice(shoe.price),
                style = MaterialTheme.typography.bodyLarge,
            )
            Row(
                modifier = Modifier.height(180.dp),
            ) {
                Spacer(
                    Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(MaterialTheme.colorScheme.onBackground),
                )
                image(defaultVariant.image)
            }
        }
    }
}

@Composable
fun ShoeCardSmall(
    shoe: Shoe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
) {
    val defaultVariant = remember(shoe) { shoe.variants.first() }
    Row(
        modifier = modifier
            .height(96.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ShoeImage(
            imageDrawable = defaultVariant.image,
            modifier = Modifier
                .width(128.dp)
                .then(imageModifier),
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = shoe.name,
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = formatPrice(shoe.price),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline,
            )
        }
    }
}

@Preview
@Composable
private fun ShoeCardPreview() {
    BillionHeartsTheme {
        ShoeCard(mockShoeData.first(), onClick = {}) {
            ShoeImage(
                imageDrawable = it,
                modifier = Modifier
                    .fillMaxHeight()
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0F, 0F)
                        scaleX = 1.5F
                        scaleY = 1.5F
                        translationX = 10F
                        translationY = -10F
                        rotationZ = -30F
                    },
            )
        }
    }
}

@Preview
@Composable
private fun SmallShoeCardPreview() {
    BillionHeartsTheme {
        ShoeCardSmall(mockShoeData.first(), onClick = {})
    }
}
