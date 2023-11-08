package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken

/**
 * A style sheet that contains style definitions.
 */
@Immutable
class StyleSheet internal constructor(
    private val colors: Map<ColorToken, ColorToken> = emptyMap(),
) {
    internal tailrec fun getColor(token: ColorToken): Color {
        val nextToken = colors[token] ?: return token.default
        return getColor(nextToken)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StyleSheet) return false

        return colors == other.colors
    }

    override fun hashCode(): Int = colors.hashCode()

    override fun toString(): String = "StyleSheet(colors=$colors)"

    companion object {
        /**
         * Constant for an empty style sheet.
         */
        val Empty = StyleSheet()

        /**
         * Returns the color corresponding to the given [token].
         */
        @Composable
        fun getColor(token: ColorToken): Color =
            LocalStyleSheet.current.getColor(token)
    }
}
