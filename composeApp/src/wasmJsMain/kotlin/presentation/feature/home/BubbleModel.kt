package presentation.feature.home

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.math.absoluteValue
import kotlin.random.Random

data class BubbleModel(
    val id: Int,
    val size: Int,
    val currentOffset: IntOffset = IntOffset.Zero,
    val velocity: Offset = Offset.Zero,
) {
    fun move(wind: IntOffset): BubbleModel {
        val nextVelocity = velocity * 0.995f // 공기 저항으로 인한 손실
        val isMinimumVelocity = velocity.x.absoluteValue + velocity.y.absoluteValue < 4

        return copy(
            currentOffset = currentOffset + IntOffset(velocity.x.toInt(), velocity.y.toInt()) + wind,
            velocity = if (isMinimumVelocity) velocity else nextVelocity
        )
    }

    companion object {
        var nextId = 1

        fun createIntoRandomPosition(area: IntSize): BubbleModel {
            val size = (80..120).random()

            val x = (0..area.width).random()
            val y = (0..area.height).random()

            return BubbleModel(
                id = nextId++,
                size = size,
                currentOffset = IntOffset(x, y),
                velocity = Offset(
                    x = -8 + Random.nextFloat() * 16,
                    y = -8 + Random.nextFloat() * 16
                ),
            )
        }

        fun create(wind: IntOffset, area: IntSize): BubbleModel {
            val size = (80..120).random()

            val startOffset = (0..(area.width + area.height)).random()
            val edgeX = if (wind.x > 0) -size else area.width
            val edgeY = if (wind.y > 0) -size else area.height

            val x = if (startOffset < area.width) startOffset else edgeX
            val y = if (startOffset > area.width) startOffset % area.height else edgeY
            val directionX = if (wind.x > 0) 1 else -1
            val directionY = if (wind.y > 0) 1 else -1

            return BubbleModel(
                id = nextId++,
                size = size,
                currentOffset = IntOffset(x, y),
                velocity = Offset(
                    x = directionX * 2 + Random.nextFloat() * 8,
                    y = directionY * 2 + Random.nextFloat() * 8
                )
            )
        }
    }
}
