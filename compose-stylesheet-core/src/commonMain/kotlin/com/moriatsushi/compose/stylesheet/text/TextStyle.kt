package com.moriatsushi.compose.stylesheet.text

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.ComponentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Text].
 */
@Immutable
class TextStyle(
    val color: Token<Color>? = null,
) : ComponentStyle {
    override fun hashCode(): Int = color.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextStyle) return false

        if (color != other.color) return false
        return true
    }

    override fun toString(): String = "TextStyle(" +
        "color=$color)"
}
