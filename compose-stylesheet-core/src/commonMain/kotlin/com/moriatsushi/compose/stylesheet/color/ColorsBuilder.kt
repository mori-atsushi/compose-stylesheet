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
    operator fun ColorToken.plusAssign(token: ColorToken) {
        colors[this] = token
    }

    /**
     * Overrides the [token][this] with the given [color].
     */
    operator fun ColorToken.plusAssign(color: Color) {
        colors[this] = color.toToken()
    }

    internal fun build(): Map<ColorToken, ColorToken> = colors
}
