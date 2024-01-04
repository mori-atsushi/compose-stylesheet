package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [ButtonStateStyle].
 */
@StyleSheetBuilderMarker
open class ButtonStateStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : ComponentCommonStyleBuilder by commonStyleHelper {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    operator fun invoke(builder: ButtonStateStyleBuilder.() -> Unit) {
        builder()
    }

    internal operator fun plusAssign(other: ButtonStateStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    internal fun buildStateStyle(): ButtonStateStyle = ButtonStateStyle(
        commonStyle = commonStyleHelper.buildCommonStyle(),
        contentStyle = content.build(),
    )
}
