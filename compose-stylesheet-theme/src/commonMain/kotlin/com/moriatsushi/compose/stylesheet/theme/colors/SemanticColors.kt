package com.moriatsushi.compose.stylesheet.theme.colors

import com.moriatsushi.compose.stylesheet.theme.Colors
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * Semantic color tokens. These colors may be changed based on the theme.
 */
class SemanticColors internal constructor() {
    val background = Token("color.semantic.background", Colors.white)
    val onBackground = Token("color.semantic.onBackground", Colors.gray900)

    val primarySurface = Token("color.semantic.primarySurface", Colors.gray50)
    val onPrimarySurface = Token("color.semantic.onPrimarySurface", Colors.gray900)
}
