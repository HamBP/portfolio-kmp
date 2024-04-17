package presentation.feature.home

import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    var area: IntSize = IntSize.Zero
    val bubbles: MutableStateFlow<List<BubbleModel>> = MutableStateFlow(emptyList())
    private var wind = IntOffset(0, -2)

    init {
        moveBubbles()
        generateBubbles()
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
                delay(1000)
                bubbles.value += BubbleModel.create(IntSize.Zero)
            }
        }
    }
}