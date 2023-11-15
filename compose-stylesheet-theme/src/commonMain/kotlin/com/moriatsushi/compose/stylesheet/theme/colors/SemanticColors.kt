package com.moriatsushi.compose.stylesheet.theme.colors

import com.moriatsushi.compose.stylesheet.theme.Colors
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * Semantic color tokens. These colors may be changed based on the theme.
 */
class SemanticColors internal constructor() {
    val primarySurface = Token("color.semantic.primarySurface", Colors.white)
    val onPrimarySurface = Token("color.semantic.onPrimarySurface", Colors.black)
}
