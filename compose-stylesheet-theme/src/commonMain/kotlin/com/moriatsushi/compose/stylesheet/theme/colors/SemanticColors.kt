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

    val inverseSurface = Token("color.semantic.inverseSurface", Colors.gray900)
    val inverseSurfacePressed = Token("color.semantic.inverseSurfacePressed", Colors.gray700)
    val onInverseSurface = Token("color.semantic.onInverseSurface", Colors.gray50)

    val disabledSurface = Token("color.semantic.inverseSurfacePressed", Colors.gray100)
    val onDisabledSurface = Token("color.semantic.onInverseSurface", Colors.gray300)

    val focus = Token("color.semantic.focus", Colors.blue300)
}
