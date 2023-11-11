package com.moriatsushi.compose.stylesheet.text

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [TextStyle].
 */
@StyleSheetBuilderMarker
class TextStyleBuilder internal constructor() : StyleBuilder<TextStyle> {
    /**
     * A text color.
     */
    val color: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: TextStyle) {
        color += other.color
    }

    override fun build(): TextStyle = TextStyle(
        color = color.token,
    )
}
