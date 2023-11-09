package com.moriatsushi.compose.stylesheet.surface

import com.moriatsushi.compose.stylesheet.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.color.ColorSetter

/**
 * A builder for [SurfaceStyle].
 */
@StyleSheetBuilderMarker
class SurfaceStyleBuilder internal constructor() : StyleBuilder<SurfaceStyle> {
    /**
     * A background color.
     */
    val backgroundColor: ColorSetter = ColorSetter()

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

    override fun build(): SurfaceStyle = SurfaceStyle(
        backgroundColor = backgroundColor.value,
        contentStyle = content.build(),
    )
}
