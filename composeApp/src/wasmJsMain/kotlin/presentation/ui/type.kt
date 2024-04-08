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

val head1 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 36.sp,
    fontWeight = FontWeight.SemiBold,
)

val head2 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 28.sp,
    fontWeight = FontWeight.SemiBold,
)

val title3 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
)

val body1 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 16.sp,
)

val body2 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 14.sp
)

val body3 = TextStyle(
    fontFamily = pretendardFamily,
    fontSize = 12.sp
)