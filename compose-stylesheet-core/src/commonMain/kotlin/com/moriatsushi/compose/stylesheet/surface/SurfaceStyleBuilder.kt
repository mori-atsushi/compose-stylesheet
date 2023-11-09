package com.moriatsushi.compose.stylesheet.surface

import com.moriatsushi.compose.stylesheet.ComponentStyleBuilder
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

    override fun build(): SurfaceStyle = SurfaceStyle(
        backgroundColor = backgroundColor.value,
    )
}
