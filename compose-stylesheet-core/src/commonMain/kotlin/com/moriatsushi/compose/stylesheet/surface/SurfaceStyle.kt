package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.ComponentStyle
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.text.TextStyle

/**
 * A style for [Surface].
 */
@Immutable
class SurfaceStyle(
    val backgroundColor: ColorToken? = null
): ComponentStyle {
    override fun hashCode(): Int = backgroundColor.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TextStyle) return false

        if (backgroundColor != other.color) return false
        return true
    }

    override fun toString(): String = "SurfaceStyle(" +
        "backgroundColor=$backgroundColor)"
}
