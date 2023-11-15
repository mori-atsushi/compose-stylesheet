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

    val semantic: SemanticColors = SemanticColors()
}
