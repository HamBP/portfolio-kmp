package presentation.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.ui.*

@Composable
fun HomeScreen(
    navigateToProjects: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.width(1000.dp).padding(top = 240.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "환영합니다",
                style = head1,
                color = Gray90,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "개발을 통해 사용자에게 가치를 만들어내는 것을 좋아합니다.\n" +
                        "그동안 진행했던 프로젝트를 통해 개발에 대한 열정을 확인해 보세요.",
                style = body1,
                color = Gray90,
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "이 페이지는 KMP를 이용하여 Compose로 만들었습니다.\n" +
                        "Kotlin Wasm은 현재 alpha 버전으로, 아직 모바일 브라우저를 지원하고 있지 않습니다.",
                style = body3,
                color = Gray60,
            )
            TextButton(
                modifier = Modifier.padding(top = 24.dp),
                onClick = navigateToProjects,
            ) {
                Text(
                    "프로젝트 보러가기",
                    style = body1,
                    color = Primary,
                )
            }
        }
    }
}