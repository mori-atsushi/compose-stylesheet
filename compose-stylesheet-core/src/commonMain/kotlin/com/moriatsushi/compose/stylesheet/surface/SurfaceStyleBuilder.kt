package com.moriatsushi.compose.stylesheet.surface

import com.moriatsushi.compose.stylesheet.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.color.ColorSetter
import com.moriatsushi.compose.stylesheet.color.ColorSetterScope

/**
 * A builder for [SurfaceStyle].
 */
@StyleSheetBuilderMarker
class SurfaceStyleBuilder internal constructor() :
    ComponentStyleBuilder<SurfaceStyle>, ColorSetterScope {
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
