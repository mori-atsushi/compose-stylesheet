package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [ButtonStyle].
 */
@StyleSheetBuilderMarker
class ButtonStyleBuilder internal constructor() : ComponentStyleBuilder<ButtonStyle>() {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    /**
     * Defines content styles.
     */
    fun content(builder: ContentStyleBuilder.() -> Unit) {
        content.builder()
    }

    override fun plusAssign(other: ButtonStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    override fun build(): ButtonStyle = ButtonStyle(
        commonStyle = buildCommonStyle(),
        contentStyle = content.build(),
    )
}
