package presentation.feature.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.component.Content
import presentation.component.SideBar

private val homeViewModel = HomeViewModel()

@Composable
fun Home(
    viewModel: HomeViewModel = homeViewModel
) {
    val currentContent by viewModel.currentContent.collectAsState()

    Row {
        SideBar(
            modifier = Modifier.width(320.dp),
            currentContent = currentContent,
            contents = viewModel.contents,
            onContentSelected = viewModel::selectContent,
        )
        Content(currentContent)
    }
}
