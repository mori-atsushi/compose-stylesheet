package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.token.Token

@Immutable
class ContentStyle(
    val color: Token<Color>? = null,
) {
    override fun hashCode(): Int = color.hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ContentStyle) return false

        if (color != other.color) return false
        return true
    }

    override fun toString(): String = "ContentStyle(" +
        "color=$color)"

    companion object {
        /**
         * Constant for a default [ContentStyle].
         */
        val Default = ContentStyle()
    }
}

/**
 * Creates a new [ContentStyle] using the given [builder].
 */
fun ContentStyle(builder: ContentStyleBuilder.() -> Unit): ContentStyle =
    ContentStyleBuilder().apply(builder).build()
