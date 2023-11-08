package com.moriatsushi.compose.stylesheet.color

import androidx.compose.ui.graphics.Color

/**
 * A builder for colors in [com.moriatsushi.compose.stylesheet.StyleSheet].
 */
class ColorsBuilder internal constructor() {
    private val colors = mutableMapOf<ColorToken, ColorToken>()

    /**
     * Overrides the [token][this] with the given [token].
     */
    infix fun ColorToken.to(token: ColorToken) {
        colors[this] = token
    }

    /**
     * Overrides the [token][this] with the given [color].
     */
    infix fun ColorToken.to(color: Color) {
        colors[this] = color.toToken()
    }

    internal fun build(): Map<ColorToken, ColorToken> = colors
}
