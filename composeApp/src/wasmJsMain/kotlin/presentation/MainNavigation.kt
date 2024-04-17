package presentation

sealed class MainNavigation(val route: String) {
    data object Projects : MainNavigation("projects")
}