package presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.algosketch.navigation.ComposeNavigator
import navigation.rememberNavController
import presentation.feature.home.HomeScreen
import presentation.feature.projects.ProjectsScreen
import presentation.ui.Gray90

@Composable
fun App() {
    MaterialTheme {
        MainNavHost()
    }
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    val backStack by navController.backStack.collectAsState()
    val currentDestination by derivedStateOf { backStack.last().destination.route }

    val homeDestination = ComposeNavigator.Destination {
        HomeScreen(
            navigateToProjects = {
                navController.navigate(MainNavigation.Projects.route)
            }
        )
    }.apply {
        route = MainNavigation.Home.route
    }

    val projectsDestination = ComposeNavigator.Destination {
        ProjectsScreen()
    }.apply {
        route = MainNavigation.Projects.route
    }

    val graph = remember { listOf(homeDestination, projectsDestination) }

    Column {
        Header(
            navigateToHome = {
                navController.navigate(MainNavigation.Home.route)
            }
        )

        val currentScreen = graph.find { it.route == currentDestination }!!
        currentScreen.content(backStack.last())
    }
}

@Composable
private fun Header(
    navigateToHome: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(64.dp).background(Gray90).padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.clickable(onClick = navigateToHome),
            text = "Home",
            color = Color.White,
        )
    }
}