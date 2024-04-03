package presentation.feature.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.model.ContentModel
import presentation.model.Link
import presentation.model.Period

class HomeViewModel {
    val contents: List<ContentModel> = listOf(
        ContentModel(
            links = listOf(
                Link("GitHub", "https://github.com/boostcampwm2023/and04-catchy-tape"),
                Link("프로젝트 문서", "https://github.com/boostcampwm2023/and04-catchy-tape"),
            ),
            title = "Catchy Tape",
            period = Period(2023, 11, 2024, 1),
            skills = listOf("MVVM", "Hilt", "Coroutine", "Media3", "ExoPlayer"),
            summary = "노래 공유 및 음악 스트리밍 서비스",
            position = "Android",
        ),
        ContentModel(
            title = "Example",
            period = Period(2023, 1, 2023, 1),
            position = "Android",
        ),
        ContentModel(
            links = listOf(
                Link("GitHub", "https://github.com/boostcampwm2023/and04-catchy-tape"),
            ),
            title = "Catchy Tape2",
            period = Period(2023, 11, 2024, 1),
            position = "Android",
        ),
        ContentModel(
            title = "Example2",
            period = Period(2023, 1, 2023, 1),
            position = "Flutter",
        ),
    )
    private val _currentContent = MutableStateFlow(contents.first())
    val currentContent: StateFlow<ContentModel> = _currentContent.asStateFlow()

    fun selectContent(content: ContentModel) {
        _currentContent.value = content
    }
}