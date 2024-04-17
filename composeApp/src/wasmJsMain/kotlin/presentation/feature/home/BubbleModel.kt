package presentation.feature.home

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

data class BubbleModel(
    val id: Int,
    val size: Int,
    val currentOffset: IntOffset = IntOffset.Zero,
    val velocity: Offset = Offset.Zero,
) {
    fun move(wind: IntOffset): BubbleModel {
        return copy(
            currentOffset = currentOffset + IntOffset(velocity.x.toInt(), velocity.y.toInt()) + wind,
            velocity = velocity * 0.99f // 공기 저항으로 인한 손실
        )
    }

    companion object {
        var nextId = 1

        fun create(area: IntSize): BubbleModel {
            val x = (0..(area.width)).random()
            val y = (0..(area.height)).random()
            val velocityRange = 10..20

            return BubbleModel(
                id = nextId++,
                size = (80..120).random(),
                currentOffset = IntOffset(x, y),
                velocity = Offset(velocityRange.random().toFloat(), velocityRange.random().toFloat()),
            )
        }
    }
}
