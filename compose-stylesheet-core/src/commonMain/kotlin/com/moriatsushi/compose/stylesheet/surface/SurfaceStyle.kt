package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.ComponentStyle
import com.moriatsushi.compose.stylesheet.ContentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Surface].
 */
@Immutable
class SurfaceStyle(
    val backgroundColor: Token<Color>? = null,
    val contentStyle: ContentStyle = ContentStyle.Default,
) : ComponentStyle {
    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + contentStyle.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SurfaceStyle) return false

        if (backgroundColor != other.backgroundColor) return false
        if (contentStyle != other.contentStyle) return false
        return true
    }

    override fun toString(): String = "SurfaceStyle(" +
        "backgroundColor=$backgroundColor" +
        "contentStyle=$contentStyle)"
}
