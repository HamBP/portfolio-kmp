package presentation.model

data class ContentModel(
    val links: List<Link> = emptyList(),
    val title: String = "",
    val period: Period,
    val skills: List<String> = emptyList(),
    val summary: String = "",
    val organization: String = "",
    val teamComposition: Map<String, Int> = emptyMap(),
    val position: String = "",
    val screenShots: List<ImageUrl> = emptyList(),
    val descriptions: List<Description> = emptyList(),
) {
    init {
        require(screenShots.size <= 4) { "사진은 4장 이하로 넣으세요." }
    }
}
