package presentation.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.model.ContentModel

@Composable
fun Home(
    viewModel: HomeViewModel = HomeViewModel()
) {
    Row {
        SideBar(
            modifier = Modifier.width(320.dp),
            contents = viewModel.contents,
        )
        Content(viewModel.currentContent)
    }
}

@Composable
fun SideBar(
    contents: List<ContentModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .padding(start = 20.dp, top = 20.dp)
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
    ) {
        items(contents.size, key = { contents[it].title }) { index ->
            Project(contents[index].title)
        }
    }
}

@Composable
fun Project(
    title: String
) {
    Text(title)
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
                Text(
                    contentModel.title,
                    style = TextStyle(
                        fontSize = 28.sp,
                    )
                )
            }

            // 정적 페이지이기 때문에 key를 넣지 않았다.
        }
    }
}
