package com.moriatsushi.compose.stylesheet.theme

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.theme.colors.SemanticColors
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * Color tokens.
 */
object Colors {
    val black = Token("color.black", Color(0xFF000000))
    val white = Token("color.white", Color(0xFFFFFFFF))

    val gray50 = Token("color.gray50", Color(0xFFF7F8F9))
    val gray100 = Token("color.gray100", Color(0xFFECEEEf))
    val gray200 = Token("color.gray200", Color(0xFFD4D6D9))
    val gray300 = Token("color.gray300", Color(0xFFADB1B6))
    val gray400 = Token("color.gray400", Color(0xFF888C91))
    val gray500 = Token("color.gray500", Color(0xFF61656A))
    val gray600 = Token("color.gray600", Color(0xFF3F4349))
    val gray700 = Token("color.gray700", Color(0xFF2D3135))
    val gray800 = Token("color.gray800", Color(0xFF1A1C1F))
    val gray900 = Token("color.gray900", Color(0xFF090A0C))

    val semantic: SemanticColors = SemanticColors()
}
