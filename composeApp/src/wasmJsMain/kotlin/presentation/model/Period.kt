package presentation.model

data class Period(
    val startYear: Int,
    val startMonth: Int,
    val endYear: Int? = null,
    val endMonth: Int? = null,
) {
    val priority = (endYear ?: 2100) * 12 + (endMonth ?: 12)

    override fun toString(): String {
        val startDate = "${startYear}-${(startMonth).toString().padStart(2, '0')}"
        val endDate = "${endYear}-${endMonth.toString().padStart(2, '0')}"

        return if (endMonth == null) "$startDate ~ 진행 중" else "$startDate ~ $endDate"
    }
}
