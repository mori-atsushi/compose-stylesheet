package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for common styles in [com.moriatsushi.compose.stylesheet.StyleSheet].
 */
@StyleSheetBuilderMarker
class ContentStyleBuilder internal constructor() : StyleBuilder<ContentStyle> {
    /**
     * A content color, which is used for text and icons.
     */
    val color: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: ContentStyle) {
        color += other.color
    }

    override fun build(): ContentStyle = ContentStyle(
        color = color.token,
    )
}
