package presentation.feature.skills

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.component.RenderMarkdown
import presentation.ui.head3

@Composable
fun SkillsScreen(
    skillsViewModel: SkillsViewModel = SkillsViewModel(),
) {
    val language by remember { mutableStateOf(skillsViewModel.languages) }
    val frameworks by remember { mutableStateOf(skillsViewModel.frameworks) }

    Box(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(1000.dp),
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "언어",
                style = head3,
            )
            RenderMarkdown(
                modifier = Modifier.padding(bottom = 40.dp),
                src = language
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "프레임워크",
                style = head3,
            )
            RenderMarkdown(
                modifier = Modifier.padding(bottom = 40.dp),
                src = frameworks
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}