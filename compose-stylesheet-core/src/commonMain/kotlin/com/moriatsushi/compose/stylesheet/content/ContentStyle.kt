package com.moriatsushi.compose.stylesheet.content

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A common style that can be applied to a component. This is provided by [LocalContentStyle] and
 * can be customized using [ProvideContentStyle].
 */
@Stable
sealed interface ContentStyle {
    val color: Token<Color>?

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

internal fun ContentStyle(
    color: Token<Color>? = null,
): ContentStyle = ContentStyleImpl(
    color = color,
)

@Immutable
private data class ContentStyleImpl(
    override val color: Token<Color>?,
) : ContentStyle
