package navigation

import androidx.compose.runtime.Composable

class ComposeNavigator {
    class Destination(
        val content: @Composable (NavBackStackEntry) -> Unit
    ) : NavDestination()
}
