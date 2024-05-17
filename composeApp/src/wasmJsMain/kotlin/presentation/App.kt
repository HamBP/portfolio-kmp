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
import navigation.NavHost
import navigation.composable
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

    Column {
        Header(
            navigateToHome = {
                navController.navigate(Home)
            }
        )

        NavHost(
            navController = navController,
            startDestination = Projects
        ) {
            composable<Home> {
                HomeScreen(
                    navigateToProjects = {
                        navController.navigate(Projects)
                    }
                )
            }

            composable<Projects> { ProjectsScreen() }
        }
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