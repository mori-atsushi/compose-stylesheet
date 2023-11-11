package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.color.ColorSetter

/**
 * A builder for common styles in [com.moriatsushi.compose.stylesheet.StyleSheet].
 */
@StyleSheetBuilderMarker
class ContentStyleBuilder internal constructor() : StyleBuilder<ContentStyle> {
    /**
     * A content color, which is used for text and icons.
     */
    val color: ColorSetter = ColorSetter()

    override fun plusAssign(other: ContentStyle) {
        color += other.color
    }

    override fun build(): ContentStyle = ContentStyle(
        color = color.value,
    )
}
