package presentation.feature.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.*
import presentation.ui.*

private val homeViewModel = HomeViewModel()

@Composable
fun HomeScreen(
    navigateToProjects: () -> Unit,
    viewModel: HomeViewModel = homeViewModel,
) {
    val bubbles by viewModel.bubbles.collectAsState()

    Box(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .onSizeChanged { viewModel.area = it },
        contentAlignment = Alignment.TopCenter,
    ) {
        Bubbles(bubbles)
        HomeContent(navigateToProjects)
    }
}

@Composable
private fun Bubbles(
    bubbles: List<BubbleModel>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        bubbles.forEach { bubble ->
            Bubble(
                modifier = Modifier.size(bubble.size.dp).offset { bubble.currentOffset }
            )
        }
    }
}

@Composable
private fun HomeContent(
    navigateToProjects: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PhoneFrame(
            modifier = Modifier.padding(top = 40.dp),
            navigateToProjects = navigateToProjects,
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        Text(
            modifier = Modifier.padding(vertical = 40.dp),
            text = "이 페이지는 Alpha 버전의 KMP(Kotlin Wasm)를 이용하여 제작되었으며, 모바일 브라우저를 지원하지 않습니다.",
            style = caption.copy(color = Gray60)
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun PhoneFrame(
    navigateToProjects: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.width(393.dp).height(760.dp).clip(RoundedCornerShape(40.dp)).background(Color.White)
                .border(
                    shape = RoundedCornerShape(40.dp),
                    color = Color.Black,
                    width = 4.dp,
                )
                .padding(horizontal = 24.dp)
        ) {
            Column {
                Image(
                    modifier = Modifier.padding(top = 4.dp),
                    painter = painterResource(Res.drawable.status_bar),
                    contentDescription = null,
                )
                Image(
                    modifier = Modifier.padding(top = 16.dp).size(100.dp).clip(CircleShape)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(Res.drawable.profile),
                    contentDescription = "프로필 사진",
                    contentScale = ContentScale.Crop,
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                    text = "송준영",
                    style = head2.copy(textAlign = TextAlign.Center),
                    color = Gray90,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "ANDROID DEVELOPER",
                    style = body2.copy(textAlign = TextAlign.Center),
                    color = Gray60,
                )
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    text = "개발을 통해 사용자에게 가치를 전달하고 싶습니다\n" +
                            "프로젝트를 통해 개발과 서비스에 대한 열정을 확인해 보세요",
                    style = body2,
                    color = Gray90,
                )
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable(onClick = navigateToProjects)
                        .align(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "프로젝트 보러가기",
                        style = body2,
                        color = Primary,
                    )
                    Icon(
                        painter = painterResource(Res.drawable.arrow_right),
                        contentDescription = null,
                        tint = Primary,
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 40.dp),
                    text = "ABOUT ME",
                    style = title3,
                    color = Gray90,
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "저는 2020년에 안드로이드로 개발을 시작하여, 현재 4년째 개발하고 있습니다. 이 기간동안 안드로이드 뿐만 아니라 웹, 서버, Flutter 등 다양한 개발 분야에서 경험을 쌓았습니다. 타 직군에서 가능한 것과 어려운 것이 무엇인지 이해하며 커뮤니케이션에 강점이 있습니다. 최근 1년간은 다시 안드로이드 개발에 집중하여, Kotlin과 안드로이드 역량 향상에 노력을 기울이고 있습니다.\n",
                    style = body2,
                    color = Gray90,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(Res.drawable.navigation_bar),
                    contentDescription = null,
                )
            }
        }

        Column {
            Box(
                modifier = Modifier
                    .padding(top = 140.dp)
                    .width(4.dp).height(100.dp)
                    .clip(shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .width(4.dp).height(50.dp)
                    .clip(shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                    .background(Color.Black)
            )
        }
    }
}

@Composable
fun Bubble(modifier: Modifier) {
    Box(
        modifier = modifier,
    ) {
        val borderBrush = Brush.radialGradient(Pair(0.7f, Color.Transparent), Pair(1f, Color(0xFFEB00FF)))

        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        Pair(0f, Color.Transparent),
                        Pair(0.15f, Color.Red),
                        Pair(0.3f, Color(0xFFFF8000)),
                        Pair(0.45f, Color.Yellow),
                        Pair(0.6f, Color.Green),
                        Pair(0.75f, Color.Blue),
                        Pair(0.9f, Color(0xFFEB00FF)),
                        Pair(1f, Color.Transparent),
                    ),
                    shape = CircleShape,
                    alpha = 0.05f,
                )
                .background(
                    brush = borderBrush,
                    shape = CircleShape,
                    alpha = 0.1f,
                )
        )
    }
}