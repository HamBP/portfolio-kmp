package presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.model.ContentModel
import presentation.ui.body2
import presentation.ui.h2


@OptIn(ExperimentalResourceApi::class)
@Composable
fun Content(
    content: ContentModel
) {
    val uriHandler = LocalUriHandler.current

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
                    content.links.forEach {
                        Text(
                            modifier = Modifier.padding(end = 24.dp)
                                .clickable { uriHandler.openUri(it.url) },
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
                        content.title,
                        style = h2,
                    )
                    Column(
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        val period = content.period
                        Text(
                            text = "${period.startYear}-${period.startMonth} ~ ${period.endYear}-${period.endMonth}",
                            style = body2.copy(color = Color.Gray),
                        )

                        Text(
                            text = content.skills.joinToString(separator = ", "),
                            style = body2.copy(color = Color.Gray),
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = content.summary,
                    style = body2
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    content.screenShots.forEach {
                        Image(
                            modifier = Modifier.width(180.dp),
                            painter = painterResource(it),
                            contentDescription = "스크린샷",
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = content.descriptions,
                    style = body2,
                )
            }
        }
    }
}
