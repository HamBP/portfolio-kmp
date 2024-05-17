package navigation

class NavGraph : NavDestination() {
    val nodes = mutableListOf<ComposeNavigator.Destination>()
    var startDestinationRoute: String? = null

    fun addDestinations(nodes: List<ComposeNavigator.Destination>) {
        this.nodes.addAll(nodes)
    }
}