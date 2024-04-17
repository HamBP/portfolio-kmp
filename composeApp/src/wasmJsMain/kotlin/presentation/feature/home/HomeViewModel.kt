package presentation.feature.home

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    var area: IntSize = IntSize.Zero
    val bubbles: MutableStateFlow<List<BubbleModel>> = MutableStateFlow(emptyList())
    private var wind = IntOffset.Zero

    init {
        moveBubbles()
        generateBubbles()
        generateWind()
    }

    private fun moveBubbles() {
        viewModelScope.launch {
            while (true) {
                delay(40)
                bubbles.value = bubbles.value.map { bubble ->
                    bubble.move(wind)
                }.filter { bubble ->
                    val x = bubble.currentOffset.x
                    val y = bubble.currentOffset.y
                    val size = bubble.size
                    x in (-size..area.width + size) && y in (-size..area.height + size)
                }
            }
        }
    }

    private fun generateBubbles() {
        viewModelScope.launch {
            while (true) {
                repeat((4..8).random()) {
                    bubbles.value += BubbleModel.create(IntSize.Zero)
                    delay(200)
                }
                delay(3000)
            }
        }
    }

    private fun generateWind() {
        viewModelScope.launch {
            while (true) {
                delay(5_000)
                val windRange = -5..-2
                wind = IntOffset(0, windRange.random())
            }
        }
    }
}