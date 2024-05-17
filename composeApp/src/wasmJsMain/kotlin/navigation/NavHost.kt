package navigation

import androidx.compose.runtime.*

@Composable
fun NavHost(
    navController: NavController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit,
) {
    val graph = remember { NavGraphBuilder().apply(builder).build() }
    navController.graph = graph

    LaunchedEffect(startDestination) {
        navController.navigate(startDestination)
    }

    val backStack by navController.backStack.collectAsState()
    val currentDestination by derivedStateOf { if (backStack.isNotEmpty()) backStack.last().destination else null }
    (currentDestination as? ComposeNavigator.Destination)?.content?.invoke(backStack.last())
}