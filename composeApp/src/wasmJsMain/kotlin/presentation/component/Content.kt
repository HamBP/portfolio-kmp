package presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import presentation.model.ContentModel
import presentation.ui.body2
import presentation.ui.h2


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
