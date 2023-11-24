package com.moriatsushi.compose.stylesheet.surface

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [SurfaceStyle].
 */
@StyleSheetBuilderMarker
class SurfaceStyleBuilder internal constructor() : ComponentStyleBuilder<SurfaceStyle>() {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    override fun plusAssign(other: SurfaceStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    override fun build(): SurfaceStyle = SurfaceStyle(
        commonStyle = buildCommonStyle(),
        contentStyle = content.build(),
    )
}
