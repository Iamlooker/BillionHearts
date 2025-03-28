package com.looker.billionhearts.ui.detail.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.looker.billionhearts.data.Variant
import com.looker.billionhearts.data.mockShoeData
import com.looker.billionhearts.ui.components.ShoeImage
import com.looker.billionhearts.ui.theme.BillionHeartsTheme

@Composable
fun VariantSelection(
    variants: List<Variant>,
    modifier: Modifier = Modifier,
    content: @Composable (Variant) -> Unit,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            items = variants,
            key = { it.id },
        ) {
            content(it)
        }
    }
}

@Composable
fun VariantItem(
    isSelected: Boolean,
    onSelect: () -> Unit,
    variant: Variant,
    modifier: Modifier = Modifier,
    maxBorderWidth: Dp = 2.dp,
    borderColor: Color = MaterialTheme.colorScheme.inverseSurface,
) {
    val transition = updateTransition(isSelected)
    val strokeWidth by transition.animateDp { if (it) maxBorderWidth else 0.dp }
    val strokeColor by transition.animateColor { if (it) borderColor else Color.Unspecified }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(
                width = 80.dp,
                height = 72.dp,
            )
            .border(
                width = strokeWidth,
                color = strokeColor,
                shape = MaterialTheme.shapes.large,
            )
            .padding(4.dp)
            .then(modifier),
    ) {
        ShoeImage(
            imageDrawable = variant.image,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .clickable(onClick = onSelect),
        )
    }
}

@Preview
@Composable
private fun VariantPreview() {
    BillionHeartsTheme {
        var selected by remember { mutableIntStateOf(1) }
        VariantSelection(mockShoeData.first().variants) {
            VariantItem(
                isSelected = selected == it.id,
                onSelect = { selected = it.id },
                variant = it,
            )
        }
    }
}
