package com.moriatsushi.compose.stylesheet.text

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.color.ColorSetter

/**
 * A builder for [TextStyle].
 */
@StyleSheetBuilderMarker
class TextStyleBuilder internal constructor() : StyleBuilder<TextStyle> {
    /**
     * A text color.
     */
    val color: ColorSetter = ColorSetter()

    override fun plusAssign(other: TextStyle) {
        color += other.color
    }

    override fun build(): TextStyle = TextStyle(
        color = color.value,
    )
}
