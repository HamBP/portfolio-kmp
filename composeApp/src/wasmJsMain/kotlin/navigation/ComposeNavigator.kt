package me.algosketch.navigation

import androidx.compose.runtime.Composable
import navigation.NavBackStackEntry
import navigation.NavDestination

class ComposeNavigator {
    class Destination(
        val content: @Composable (NavBackStackEntry) -> Unit
    ) : NavDestination()
}
