package presentation.feature.home

import presentation.model.ContentModel
import presentation.model.Period

class HomeViewModel {
    val contents: List<ContentModel> = listOf(
        ContentModel(
            title = "Catchy Tape",
            period = Period(0, 0, 0, 0),
        ),
        ContentModel(
            title = "Example",
            period = Period(0, 0, 0, 0),
        ),
    )
    val currentContent: ContentModel = contents.first()
}