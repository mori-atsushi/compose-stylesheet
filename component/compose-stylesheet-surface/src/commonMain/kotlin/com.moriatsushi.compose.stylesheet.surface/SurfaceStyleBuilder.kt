package com.moriatsushi.compose.stylesheet.surface

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder

/**
 * A builder for [SurfaceStyle].
 */
@StyleSheetBuilderMarker
class SurfaceStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : StyleBuilder<SurfaceStyle>, ComponentCommonStyleBuilder by commonStyleHelper {
    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    override fun plusAssign(other: SurfaceStyle) {
        this += other.commonStyle
        content += other.contentStyle
    }

    override fun build(): SurfaceStyle = SurfaceStyle(
        commonStyle = commonStyleHelper.buildCommonStyle(),
        contentStyle = content.build(),
    )
}
