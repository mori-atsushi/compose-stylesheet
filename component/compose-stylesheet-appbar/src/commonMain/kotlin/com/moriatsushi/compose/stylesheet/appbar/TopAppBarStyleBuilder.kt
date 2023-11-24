package com.moriatsushi.compose.stylesheet.appbar

import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [TopAppBarStyle].
 */
class TopAppBarStyleBuilder internal constructor() : ComponentStyleBuilder<TopAppBarStyle>() {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    override fun plusAssign(other: TopAppBarStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    override fun build(): TopAppBarStyle = TopAppBarStyle(
        commonStyle = buildCommonStyle(),
        contentStyle = content.build(),
    )
}
