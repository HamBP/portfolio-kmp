package navigation

import androidx.compose.runtime.*

@Composable
fun NavHost(
    navController: NavController,
    startDestination: Any,
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(navController, startDestination::class.simpleName.toString(), builder)
}

@Composable
fun NavHost(
    navController: NavController,
    startDestination: String,
    builder: NavGraphBuilder.() -> Unit,
) {
    navController.graph = remember { NavGraphBuilder(startDestination).apply(builder).build() }

    val backStack by navController.backStack.collectAsState()
    val currentDestination by derivedStateOf { if (backStack.isNotEmpty()) backStack.last().destination else null }
    (currentDestination as? ComposeNavigator.Destination)?.content?.invoke(backStack.last())
}