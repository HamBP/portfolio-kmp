package presentation.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.sp
import pretendardRegular
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@OptIn(ExperimentalEncodingApi::class)
val pretendardFamily = FontFamily(
    Font(
        identity = "Pretendard-Regular",
        data = Base64.decode(pretendardRegular),
        weight = FontWeight.Normal,
    ),
    Font(
        identity = "Pretendard-Semibold",
        data = Base64.decode(pretendardSemiBold),
        weight = FontWeight.SemiBold
    ),
)

val h2 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 28.sp,
    fontWeight = FontWeight.SemiBold,
)

val h3 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
)

val body2 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 14.sp
)