package navigation

import androidx.compose.runtime.Composable
import me.algosketch.navigation.ComposeNavigator

fun NavGraphBuilder.composable(
    route: String,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    addDestination(
        ComposeNavigator.Destination(content = content).apply {
            this.route = route
        }
    )
}

class NavGraphBuilder {
    private val destinations = mutableListOf<ComposeNavigator.Destination>()

    fun addDestination(destination: ComposeNavigator.Destination) {
        destinations.add(destination)
    }

    fun build(): List<ComposeNavigator.Destination> {
        return destinations.toList()
    }
}