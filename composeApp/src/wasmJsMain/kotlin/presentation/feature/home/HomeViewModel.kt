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
        bubbles.value =
            listOf(BubbleModel.create(IntSize.Zero), BubbleModel.create(IntSize.Zero), BubbleModel.create(IntSize.Zero))
        moveBubbles()
    }

    private fun moveBubbles() {
        viewModelScope.launch {
            while (true) {
                delay(40)
                bubbles.value = bubbles.value.map { bubble ->
                    bubble.move(wind)
                }
            }
        }
    }
}