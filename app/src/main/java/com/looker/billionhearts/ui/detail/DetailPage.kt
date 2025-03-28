package com.looker.billionhearts.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.looker.billionhearts.data.Shoe
import com.looker.billionhearts.data.Variant
import com.looker.billionhearts.data.mockShoeData
import com.looker.billionhearts.ui.components.ShoeImage
import com.looker.billionhearts.ui.components.formatPrice
import com.looker.billionhearts.ui.components.imageKey
import com.looker.billionhearts.ui.detail.components.AddToBagButton
import com.looker.billionhearts.ui.detail.components.ExpandableText
import com.looker.billionhearts.ui.detail.components.SizeButton
import com.looker.billionhearts.ui.detail.components.VariantItem
import com.looker.billionhearts.ui.detail.components.VariantSelection
import com.looker.billionhearts.ui.detail.components.rememberExpandableTextState
import com.looker.billionhearts.ui.theme.BillionHeartsTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsPage(
    shoe: Shoe,
    animationScope: AnimatedContentScope,
    sharedTransitionScope: SharedTransitionScope,
    onAddToBag: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedVariant by remember { mutableIntStateOf(1) }
    var selectedSize by remember { mutableIntStateOf(shoe.availableSizes.first()) }
    with(sharedTransitionScope) {
        Details(
            modifier = modifier,
            shoe = shoe,
            animationScope = animationScope,
            selectedVariantId = selectedVariant,
            onSelectVariant = { selectedVariant = it },
            selectedSize = selectedSize,
            onSelectSize = { selectedSize = it },
            onAddToBag = onAddToBag,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.Details(
    shoe: Shoe,
    selectedVariantId: Int,
    animationScope: AnimatedContentScope,
    onSelectVariant: (Int) -> Unit,
    selectedSize: Int,
    onSelectSize: (Int) -> Unit,
    onAddToBag: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val annotatedDescription = remember { AnnotatedString(shoe.description) }
    val descriptionState = rememberExpandableTextState(annotatedDescription)
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .then(modifier),
    ) {
        val defaultVariant =
            remember(selectedVariantId) { shoe.variants.find { it.id == selectedVariantId }!! }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(92.dp))
            Header(
                shoeId = shoe.id,
                variant = defaultVariant,
                animationScope = animationScope,
            ) {
                ShoeImage(
                    imageDrawable = defaultVariant.image,
                    key = imageKey(shoe.id),
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .height(256.dp)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(imageKey(shoe.id)),
                            animatedVisibilityScope = animationScope,
                        ),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = shoe.name,
                    style = MaterialTheme.typography.headlineLarge,
                    // Assuming not larger than 2 lines
                    maxLines = 2,
                )
                Text(
                    text = formatPrice(shoe.price),
                    style = MaterialTheme.typography.headlineSmall,
                    // Hopefully
                    maxLines = 1,
                )
            }
            Spacer(Modifier.height(12.dp))
            ExpandableText(descriptionState)
            Spacer(Modifier.height(16.dp))
            VariantSelection(shoe.variants) { variant ->
                VariantItem(
                    isSelected = variant.id == selectedVariantId,
                    onSelect = { onSelectVariant(variant.id) },
                    variant = variant,
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Select Size",
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(Modifier.height(12.dp))
            SizeSelection(
                selectedSize = selectedSize,
                onSelect = onSelectSize,
                sizes = shoe.availableSizes,
            )
            Spacer(Modifier.height((48 + 24).dp))
        }
        AddToBagButton(
            onClick = { onAddToBag(shoe.id) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp),
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.Header(
    shoeId: Int,
    variant: Variant,
    animationScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    image: @Composable () -> Unit,
) {
    var transform by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        transform = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .then(modifier),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .offset(100.dp, (-180).dp)
                .sharedElement(
                    state = rememberSharedContentState("color-${shoeId}"),
                    animatedVisibilityScope = animationScope,
                )
                .size(750.dp)
                .clip(CircleShape)
                .background(variant.color),
        )
        image()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SizeSelection(
    selectedSize: Int,
    onSelect: (Int) -> Unit,
    sizes: List<Int>,
    modifier: Modifier = Modifier,
) {
    val allSizes = remember { (6..13).toList() }
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        allSizes.forEach { shoeSize ->
            val isAvailable = remember(shoeSize) { shoeSize !in sizes }
            SizeButton(
                shoeSize = shoeSize,
                isEnabled = isAvailable,
                isSelected = selectedSize == shoeSize,
                onSelect = { onSelect(shoeSize) },
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
val shoeTransform = BoundsTransform { initial, target ->
    keyframes {
        durationMillis = 500
        initial at 0
        Rect(initial.left, target.top, initial.left + target.width, target.bottom) at 300
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun DetailsPreview() {
    BillionHeartsTheme {
        val shoe = mockShoeData.first()
        var selected by remember { mutableIntStateOf(1) }
        var selectedSize by remember { mutableIntStateOf(shoe.availableSizes.first()) }
        SharedTransitionLayout {
            AnimatedContent(true) {
                it
                Details(
                    shoe = shoe,
                    animationScope = this,
                    selectedVariantId = selected,
                    onSelectVariant = { selected = it },
                    selectedSize = selectedSize,
                    onSelectSize = { selectedSize = it },
                    onAddToBag = {},
                )
            }
        }
    }
}
