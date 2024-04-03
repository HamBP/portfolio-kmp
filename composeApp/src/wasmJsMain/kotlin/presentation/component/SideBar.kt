package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import presentation.model.ContentModel
import presentation.ui.body2

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
            },
        contentPadding = PaddingValues(top = 20.dp)
    ) {
        contents.groupBy { it.position }
            .toList()
            .map { Pair(it.first, it.second.sortedByDescending { it.period.endYear * 12 + it.period.endMonth }) }
            .forEach { entry ->
                val (position, positionedContents) = entry
                item { Position(position) }
                items(positionedContents.size, key = { positionedContents[it].title }) { index ->
                    Project(
                        content = positionedContents[index],
                        selected = currentContent == positionedContents[index],
                        onContentSelected = onContentSelected,
                    )
                }
            }
    }
}

@Composable
fun Position(position: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .padding(start = 20.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(position)
    }
}

@Composable
fun Project(
    content: ContentModel,
    onContentSelected: (ContentModel) -> Unit,
    selected: Boolean = false,
) {
    val color = if (selected) Color(0xFFFFFFFF) else Color(0xFF171717)
    val background = if (selected) Color(0xFF7471F8) else Color(0xFFFFFFFF)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(background)
            .clickable { onContentSelected(content) }
            .padding(start = 36.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(
            "${content.title} (${content.period.endYear}-${content.period.endMonth})",
            style = body2.copy(color = color)
        )
    }
}
