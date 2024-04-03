package presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.model.ContentModel
import presentation.ui.body2
import presentation.ui.h2
import presentation.ui.pretendardFamily

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

@Composable
fun Content(
    contentModel: ContentModel
) {
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        LazyColumn(
            modifier = Modifier.width(800.dp)
        ) {
            item {
                Row {
                    contentModel.links.forEach {
                        Text(
                            modifier = Modifier.padding(end = 24.dp),
                            text = it.name,
                            style = body2.copy(textDecoration = TextDecoration.Underline)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.padding(top = 32.dp),
                ) {
                    Text(
                        contentModel.title,
                        style = h2,
                    )
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        val period = contentModel.period
                        Text(
                            text = "${period.startYear}-${period.startMonth} ~ ${period.endYear}-${period.endMonth}",
                            style = body2.copy(color = Color.Gray),
                        )

                        Text(
                            text = contentModel.skills.joinToString(separator = ", "),
                            style = body2.copy(color = Color.Gray),
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = contentModel.summary,
                    style = body2
                )
            }

            // 정적 페이지이기 때문에 key를 넣지 않았다.
        }
    }
}
