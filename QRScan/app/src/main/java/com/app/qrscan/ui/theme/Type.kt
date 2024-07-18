package com.app.qrscan.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.app.qrscan.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

object AppTypography {
    val RobotoFontFamily = FontFamily(
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_extra_bold, FontWeight.ExtraBold),
        Font(R.font.roboto_black, FontWeight.Black),
        Font(R.font.roboto_light, FontWeight.Light),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_semibold, FontWeight.SemiBold),
        Font(R.font.roboto_thin, FontWeight.Thin),
        Font(R.font.roboto_regular),
        Font(R.font.roboto_italic),
    )

    private val fontFamilies = mutableMapOf<String, FontFamily>(
        "Roboto" to RobotoFontFamily
    )

    private var defaultFontFamily: FontFamily? = null
    fun setAppFontFamily(fontName: String?) {
        defaultFontFamily = if (fontFamilies.containsKey(fontName)) {
            fontFamilies[fontName]!!
        } else {
            FontFamily.Default
        }
    }

    fun getDefaultFontFamily(
    ): FontFamily? {
        return defaultFontFamily
    }

    fun fontStyle(
        fontFamily: FontFamily? = defaultFontFamily,
        fontWeight: FontWeight = FontWeight.Normal,
        fontSize: TextUnit = 14.sp,
        lineHeight: TextUnit = TextUnit.Unspecified,
        color: Color = Color.Unspecified,
        letterSpacing: TextUnit = TextUnit.Unspecified
    ): TextStyle {
        return TextStyle(
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            fontSize = fontSize,
            lineHeight = lineHeight,
            color = color,
            letterSpacing = letterSpacing,
        )
    }
}