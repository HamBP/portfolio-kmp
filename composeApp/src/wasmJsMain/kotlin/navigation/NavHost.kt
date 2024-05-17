package navigation

import androidx.compose.runtime.*
import me.algosketch.navigation.ComposeNavigator

@Composable
fun NavHost(
    navController: NavController,
    builder: NavGraphBuilder.() -> Unit,
) {
    val graph = remember { NavGraphBuilder().apply(builder).build() }

    val backStack by navController.backStack.collectAsState()
    val currentDestination by derivedStateOf { backStack.last().destination.route }

    val currentScreen = graph.find { it.route == currentDestination }!!
    (currentScreen as? ComposeNavigator.Destination)?.content?.invoke(backStack.last())
}