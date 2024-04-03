package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.model.ContentModel

@Composable
fun SideBar(
    currentContent: ContentModel,
    contents: List<ContentModel>,
    onContentSelected: (ContentModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .drawBehind {
                val width = size.width
                val height = size.height

                drawLine(
                    Color.LightGray,
                    Offset(width, 0f),
                    Offset(width, height),
                    1.dp.toPx()
                )
            }
        ,
        contentPadding = PaddingValues(top = 20.dp)
    ) {
        items(contents.size, key = { contents[it].title }) { index ->
            Project(
                content = contents[index],
                selected = currentContent == contents[index],
                onContentSelected = onContentSelected,
            )
        }
    }
}

@Composable
fun Project(
    content: ContentModel,
    onContentSelected: (ContentModel) -> Unit,
    selected: Boolean = false,
) {
    val background = if (selected) Color(0xFFBFBFBF) else Color(0xFFFFFFFF)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .clickable { onContentSelected(content) }
            .padding(start = 20.dp)
    ) {
        Text(content.title)
    }
}
