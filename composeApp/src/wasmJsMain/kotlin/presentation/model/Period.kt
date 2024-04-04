package presentation.model

data class Period(
    val startYear: Int,
    val startMonth: Int,
    val endYear: Int,
    val endMonth: Int,
) {
    override fun toString(): String {
        return "${startYear}-${(startMonth).toString().padStart(2, '0')} ~ " +
                "${endYear}-${endMonth.toString().padStart(2, '0')}"
    }
}
