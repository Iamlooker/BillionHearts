package com.looker.billionhearts.ui.detail.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.looker.billionhearts.data.mockShoeData
import com.looker.billionhearts.ui.detail.DetailsPage

fun NavController.navigateToDetail(shoeId: Int, navOptions: NavOptions? = null) {
    navigate(DetailRoute(shoeId), navOptions)
}

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.details(
    sharedTransitionScope: SharedTransitionScope,
    onAddToBag: (Int) -> Unit,
) {
    composable<DetailRoute>(
        enterTransition = { fadeIn() + slideInVertically(initialOffsetY = { it / 2 }) },
        exitTransition = { fadeOut() + slideOutVertically(targetOffsetY = { it / 2 }) }
    ) {
        val route = it.toRoute<DetailRoute>()
        DetailsPage(
            shoe = mockShoeData.find { it.id == route.shoeId }!!,
            sharedTransitionScope = sharedTransitionScope,
            animationScope = this,
            onAddToBag = onAddToBag,
        )
    }
}
