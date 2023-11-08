package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken

/**
 * A style sheet that contains style definitions.
 */
class StyleSheet internal constructor(
    private val colors: Map<ColorToken, ColorToken> = emptyMap(),
) {
    internal tailrec fun getColor(token: ColorToken): Color {
        val nextToken = colors[token] ?: return token.default
        return getColor(nextToken)
    }
}
