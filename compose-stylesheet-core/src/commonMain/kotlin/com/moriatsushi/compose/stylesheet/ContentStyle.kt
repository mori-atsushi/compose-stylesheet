package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.color.ColorToken

@Immutable
class ContentStyle(
    val color: ColorToken = ColorToken.Unspecified,
) {
    /**
     * Returns a new [ContentStyle] that is a combination of this style and the given [other] style.
     */
    fun merge(other: ContentStyle): ContentStyle {
        val currentStyle = this
        return ContentStyleBuilder().apply {
            this += currentStyle
            this += other
        }.build()
    }

    /**
     * Returns a new [ContentStyle] that is a combination of this style and the style built by
     * [builder].
     */
    fun merge(builder: ContentStyleBuilder.() -> Unit): ContentStyle {
        val currentStyle = this
        return ContentStyleBuilder().apply {
            this += currentStyle
            builder()
        }.build()
    }

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

        operator fun invoke(builder: ContentStyleBuilder.() -> Unit): ContentStyle =
            ContentStyleBuilder().apply(builder).build()
    }
}
