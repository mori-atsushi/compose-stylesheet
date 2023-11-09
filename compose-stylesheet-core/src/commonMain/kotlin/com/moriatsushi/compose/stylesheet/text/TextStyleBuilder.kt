package com.moriatsushi.compose.stylesheet.text

import com.moriatsushi.compose.stylesheet.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.color.ColorSetter
import com.moriatsushi.compose.stylesheet.color.ColorSetterScope

/**
 * A builder for [TextStyle].
 */
@StyleSheetBuilderMarker
class TextStyleBuilder internal constructor() :
    ComponentStyleBuilder<TextStyle>, ColorSetterScope {
    /**
     * A text color.
     */
    val color: ColorSetter = ColorSetter()

    override fun build(): TextStyle = TextStyle(
        color = color.value,
    )
}
