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
    private var _graph: NavGraph? = null
    var graph get() = _graph!!
        set(value) {
            _graph = value
        }

    private val _backStack = MutableStateFlow(listOf<NavBackStackEntry>())
    val backStack = _backStack.asStateFlow()

    fun navigate(route: String) {
        _backStack.value += NavBackStackEntry(graph.nodes.find { it.route == route }!!)
    }
}