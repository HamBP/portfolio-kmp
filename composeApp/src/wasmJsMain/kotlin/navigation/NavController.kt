package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.MainNavigation

@Composable
fun rememberNavController(): NavController {
    return remember { NavController() }
}

class NavController {
    private val _backStack =
        MutableStateFlow(listOf(NavBackStackEntry(NavDestination().apply { route = MainNavigation.Home.route })))
    val backStack = _backStack.asStateFlow()

    fun navigate(route: String) {
        _backStack.value += NavBackStackEntry(NavDestination().apply { this.route = route })
    }
}