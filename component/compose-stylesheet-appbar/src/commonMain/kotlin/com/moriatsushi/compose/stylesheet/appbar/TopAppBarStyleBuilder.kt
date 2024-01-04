package com.moriatsushi.compose.stylesheet.appbar

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [TopAppBarStyle].
 */
class TopAppBarStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : StyleBuilder<TopAppBarStyle>, ComponentCommonStyleBuilder by commonStyleHelper {
    /**
     * A layout of the top app bar.
     */
    val layout: TopAppBarLayoutBuilder = TopAppBarLayoutBuilder()

    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    override fun plusAssign(other: TopAppBarStyle) {
        layout += other.layout
        this += other.commonStyle
        content += other.contentStyle
    }

    override fun build(): TopAppBarStyle = TopAppBarStyle(
        layout = layout.build(),
        commonStyle = commonStyleHelper.buildCommonStyle(),
        contentStyle = content.build(),
    )
}
