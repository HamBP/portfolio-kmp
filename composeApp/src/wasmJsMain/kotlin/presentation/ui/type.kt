package presentation.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp
import pretendardRegular
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@OptIn(ExperimentalEncodingApi::class)
val pretendardFamily = FontFamily(
    Font(
        identity = "Pretendard-Regular",
        data = Base64.decode(pretendardRegular)
    )
)

val h2 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 28.sp,
)

val h3 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 16.sp,
)

val body2 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 14.sp
)