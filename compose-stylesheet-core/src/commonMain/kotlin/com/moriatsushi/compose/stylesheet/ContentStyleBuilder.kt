package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.color.ColorSetter
import com.moriatsushi.compose.stylesheet.color.ColorSetterScope

/**
 * A builder for common styles in [com.moriatsushi.compose.stylesheet.StyleSheet].
 */
@StyleSheetBuilderMarker
class ContentStyleBuilder internal constructor() : ColorSetterScope {
    /**
     * A content color, which is used for text and icons.
     */
    val color: ColorSetter = ColorSetter()

    internal fun build(): ContentStyle = ContentStyle(
        color = color.value,
    )
}
