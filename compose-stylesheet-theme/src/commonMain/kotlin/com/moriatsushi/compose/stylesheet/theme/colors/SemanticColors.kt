package com.moriatsushi.compose.stylesheet.theme.colors

import com.moriatsushi.compose.stylesheet.theme.Colors
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * Semantic color tokens. These colors may be changed based on the theme.
 */
class SemanticColors internal constructor() {
    val background = Token("color.semantic.background", Colors.white)
    val onBackground = Token("color.semantic.onBackground", Colors.gray900)
    val onBackgroundPressed = Token("color.semantic.onBackgroundPressed", Colors.gray500)
    val onBackgroundHovered = Token("color.semantic.onBackgroundHovered", Colors.gray700)
    val onBackgroundDisabled = Token("color.semantic.onBackgroundDisabled", Colors.gray300)

    val primarySurface = Token("color.semantic.primarySurface", Colors.gray50)
    val onPrimarySurface = Token("color.semantic.onPrimarySurface", Colors.gray900)

    val inverseSurface = Token("color.semantic.inverseSurface", Colors.gray900)
    val inverseSurfaceHovered = Token("color.semantic.inverseSurfaceHovered", Colors.gray700)
    val inverseSurfacePressed = Token("color.semantic.inverseSurfacePressed", Colors.gray600)
    val onInverseSurface = Token("color.semantic.onInverseSurface", Colors.gray50)

    val disabledSurface = Token("color.semantic.disabledSurface", Colors.gray100)
    val onDisabledSurface = Token("color.semantic.onDisabledSurface", Colors.gray300)

    val focus = Token("color.semantic.focus", Colors.blue300)
}
