package com.looker.billionhearts.ui.explore.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.looker.billionhearts.data.mockShoeData
import com.looker.billionhearts.ui.explore.ExplorePage

fun NavController.navigateToExplore(navOptions: NavOptions? = null) {
    navigate(ExploreRoute, navOptions)
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.explore(
    sharedTransitionScope: SharedTransitionScope,
    onShoeClick: (Int) -> Unit,
) {
    composable<ExploreRoute> {
        ExplorePage(
            highlights = mockShoeData.take(3),
            shoes = mockShoeData.drop(3),
            sharedTransitionScope = sharedTransitionScope,
            animationScope = this,
            onShoeClick = onShoeClick,
        )
    }
}
