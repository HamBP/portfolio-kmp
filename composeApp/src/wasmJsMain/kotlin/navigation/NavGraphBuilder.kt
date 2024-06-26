package navigation

import androidx.compose.runtime.Composable

inline fun <reified T: Any> NavGraphBuilder.composable(
    noinline content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(route = T::class.simpleName.toString(), content = content)
}

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

class NavGraphBuilder(
    private val startDestination: String
) {
    private val destinations = mutableListOf<ComposeNavigator.Destination>()

    fun addDestination(destination: ComposeNavigator.Destination) {
        destinations.add(destination)
    }

    fun build() = NavGraph().apply {
        addDestinations(destinations)
        startDestinationRoute = startDestination
    }
}