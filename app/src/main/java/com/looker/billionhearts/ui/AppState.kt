package com.looker.billionhearts.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.looker.billionhearts.ui.explore.navigation.ExploreRoute

@Composable
fun rememberAppState(
    navController: NavController = rememberNavController(),
): AppState = remember(navController) {
    AppState(navController)
}

@Immutable
class AppState(
    val controller: NavController,
) {

    val isHome: Boolean
        @Composable get() = currentDestination?.hasRoute(ExploreRoute::class) == true

    private val currentDestination: NavDestination?
        @Composable get() = controller.currentBackStackEntryAsState().value?.destination

}
