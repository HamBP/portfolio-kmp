package presentation.feature.projects

import data.ProjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import presentation.model.ContentModel

class ProjectsViewModel(
    projectRepository: ProjectRepository = ProjectRepository(),
) {
    val contents: List<ContentModel> = projectRepository.getProjects()
    private val _currentContent = MutableStateFlow(contents.first())
    val currentContent: StateFlow<ContentModel> = _currentContent.asStateFlow()

    fun selectContent(content: ContentModel) {
        _currentContent.value = content
    }
}