package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [ButtonStateStyle].
 */
@StyleSheetBuilderMarker
class ButtonStateStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : ComponentCommonStyleBuilder by commonStyleHelper {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    operator fun plusAssign(other: ButtonStateStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    operator fun invoke(builder: ButtonStateStyleBuilder.() -> Unit) {
        builder()
    }

    fun build(): ButtonStateStyle = ButtonStateStyle(
        commonStyle = commonStyleHelper.buildCommonStyle(),
        contentStyle = content.build(),
    )
}
