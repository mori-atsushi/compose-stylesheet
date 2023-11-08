package com.moriatsushi.compose.stylesheet.text

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.color.ColorToken

/**
 * A style for [Text].
 */
@Immutable
class TextStyle(
    val color: ColorToken? = null,
) {
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
