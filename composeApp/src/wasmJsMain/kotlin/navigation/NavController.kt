package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun rememberNavController(): NavController {
    return remember { NavController() }
}

class NavController {
    private var _graph: NavGraph? = null
    var graph
        get() = _graph!!
        set(value) {
            if (value != _graph) {
                _graph = value
                navigate(graph.startDestinationRoute!!)
            }
        }

    private val _backStack = MutableStateFlow(listOf<NavBackStackEntry>())
    val backStack = _backStack.asStateFlow()

    fun <T : Any> navigate(route: T) {
        navigate(route::class.simpleName.toString())
    }

    fun navigate(route: String) {
        _backStack.value += NavBackStackEntry(graph.nodes.find { it.route == route }!!)
    }
}