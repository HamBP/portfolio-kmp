package presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import presentation.feature.projects.ProjectsScreen

@Composable
fun App() {
    MaterialTheme {
        MainNavHost()
    }
}

@Composable
fun MainNavHost() {
    var currentNav by remember { mutableStateOf<MainNavigation>(MainNavigation.Projects) }

    Column {
        when (currentNav) {
            MainNavigation.Projects -> ProjectsScreen()
        }
    }
}