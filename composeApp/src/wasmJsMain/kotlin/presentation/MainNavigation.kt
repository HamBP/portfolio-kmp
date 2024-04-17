package presentation

sealed class MainNavigation(val route: String) {
    data object Home : MainNavigation("home")
    data object Projects : MainNavigation("projects")
}