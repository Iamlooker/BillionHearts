package com.looker.billionhearts.ui.explore

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.looker.billionhearts.data.Shoe
import com.looker.billionhearts.data.mockCategories
import com.looker.billionhearts.ui.components.ShoeImage
import com.looker.billionhearts.ui.components.imageKey
import com.looker.billionhearts.ui.explore.components.ShoeCard
import com.looker.billionhearts.ui.explore.components.ShoeCardSmall

private val paddingModifier = Modifier.padding(horizontal = 16.dp)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ExplorePage(
    highlights: List<Shoe>,
    shoes: List<Shoe>,
    sharedTransitionScope: SharedTransitionScope,
    animationScope: AnimatedContentScope,
    onShoeClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    with(sharedTransitionScope) {
        Explore(
            modifier = modifier,
            highlights = highlights,
            animationScope = animationScope,
            onShoeClick = onShoeClick,
            shoes = shoes,
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
private fun SharedTransitionScope.Explore(
    modifier: Modifier,
    highlights: List<Shoe>,
    animationScope: AnimatedContentScope,
    onShoeClick: (Int) -> Unit,
    shoes: List<Shoe>,
) {
    var selectedLabel by remember { mutableStateOf(mockCategories.first()) }
    with(this) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 92.dp),
        ) {
            item {
                Header(modifier = paddingModifier) {
                    ChipRow { label ->
                        Chip(
                            label = label,
                            isSelected = label == selectedLabel,
                            onSelect = { selectedLabel = label },
                        )
                    }
                }
            }
            item {
                Highlights(
                    modifier = modifier,
                    pagerState = rememberPagerState { highlights.size },
                    highlights = highlights,
                    animationScope = animationScope,
                    onShoeClick = onShoeClick,
                )
            }
            item {
                val headerText = remember(shoes.size) { "${shoes.size} OPTIONS" }
                Column(modifier = paddingModifier) {
                    Text(
                        text = headerText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline,
                    )
                    Spacer(Modifier.height(12.dp))
                    HorizontalDivider()
                }
            }
            items(
                items = shoes,
                key = { it.id },
            ) { shoe ->
                Column(modifier = paddingModifier) {
                    ShoeCardSmall(
                        shoe = shoe,
                        onClick = { onShoeClick(shoe.id) },
                        imageModifier = Modifier
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(imageKey(shoe.id)),
                                animatedVisibilityScope = animationScope,
                            ),
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Shoes",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(Modifier.height(4.dp))
        content()
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun SharedTransitionScope.Highlights(
    pagerState: PagerState,
    highlights: List<Shoe>,
    animationScope: AnimatedContentScope,
    onShoeClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSize = PageSize.Fixed(268.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { page ->
        val shoe = remember { highlights[page] }
        ShoeCard(
            backgroundModifier = Modifier.sharedElement(
                state = rememberSharedContentState("color-${shoe.id}"),
                animatedVisibilityScope = animationScope,
            ),
            shoe = shoe,
            onClick = { onShoeClick(shoe.id) },
        ) { image ->
            ShoeImage(
                imageDrawable = image,
                key = imageKey(shoe.id),
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(imageKey(shoe.id)),
                        animatedVisibilityScope = animationScope,
                    )
                    .graphicsLayer {
                        val startOffset = pagerState.offsetForPage(page)
                        translationX = 20F + size.width * (-startOffset * 0.1F)

                        val scale = 1.1F - (startOffset * 0.1F)
                        scaleX = scale
                        scaleY = scale
                        rotationZ = -25F + (startOffset * 25F)
                    },
            )
        }
    }
}

@Composable
private fun ChipRow(
    modifier: Modifier = Modifier,
    content: @Composable (label: String) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        mockCategories.forEach { label ->
            content(label)
        }
    }
}

@Composable
private fun Chip(
    label: String,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
    colors: SelectableChipColors = chipColor,
    border: BorderStroke = InputChipDefaults.inputChipBorder(enabled = true, selected = isSelected),
) {
    InputChip(
        selected = isSelected,
        onClick = onSelect,
        modifier = modifier,
        colors = colors,
        border = border,
        shape = CircleShape,
        label = {
            Text(
                text = label,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
            )
        },
    )
}

@Stable
private val chipColor
    @Composable
    get() = InputChipDefaults.inputChipColors(
        selectedContainerColor = MaterialTheme.colorScheme.inverseSurface,
        selectedLabelColor = MaterialTheme.colorScheme.inverseOnSurface,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        labelColor = MaterialTheme.colorScheme.outline,
    )

@Stable
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction
