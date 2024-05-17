package navigation

import androidx.compose.runtime.Composable
import me.algosketch.navigation.ComposeNavigator

fun composable(
    route: String,
    content: @Composable (NavBackStackEntry) -> Unit,
): ComposeNavigator.Destination {
    return ComposeNavigator.Destination(content = content).apply {
        this.route = route
    }
}