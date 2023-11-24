package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [ButtonStateStyle].
 */
@StyleSheetBuilderMarker
class ButtonStateStyleBuilder internal constructor() : ComponentStyleBuilder<ButtonStateStyle>() {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    override fun plusAssign(other: ButtonStateStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    override fun build(): ButtonStateStyle = ButtonStateStyle(
        commonStyle = buildCommonStyle(),
        contentStyle = content.build(),
    )
}
