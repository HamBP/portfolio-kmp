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
    private val _backStack = MutableStateFlow(listOf("home"))
    val backStack = _backStack.asStateFlow()

    fun navigate(route: String) {
        _backStack.value += route
    }
}