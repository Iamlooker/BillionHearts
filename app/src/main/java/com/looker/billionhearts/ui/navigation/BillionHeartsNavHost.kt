package com.looker.billionhearts.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.looker.billionhearts.ui.AppState
import com.looker.billionhearts.ui.detail.navigation.details
import com.looker.billionhearts.ui.detail.navigation.navigateToDetail
import com.looker.billionhearts.ui.explore.navigation.ExploreRoute
import com.looker.billionhearts.ui.explore.navigation.explore

private val singleTopNavOptions = navOptions {
    launchSingleTop = true
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun BillionHeartsNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: Any = ExploreRoute,
) {
    SharedTransitionLayout {
        NavHost(
            navController = appState.controller as NavHostController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            explore(this@SharedTransitionLayout) { shoeId ->
                appState.controller.navigateToDetail(shoeId, singleTopNavOptions)
            }
            details(this@SharedTransitionLayout) {

            }
        }
    }
}
