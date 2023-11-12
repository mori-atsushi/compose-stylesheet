package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [SurfaceStyle].
 */
@StyleSheetBuilderMarker
class SurfaceStyleBuilder internal constructor() : StyleBuilder<SurfaceStyle> {
    /**
     * A background color.
     */
    val background: TokenSetter<Color> = TokenSetter()

    /**
     * A surface shape.
     */
    val shape: TokenSetter<Shape?> = TokenSetter()

    /**
     * A border.
     */
    val border: TokenSetter<BorderStroke?> = TokenSetter()

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

    override fun plusAssign(other: SurfaceStyle) {
        background += other.background
        shape += other.shape
        border += other.border
        content += other.contentStyle
    }

    override fun build(): SurfaceStyle = SurfaceStyle(
        background = background.token,
        shape = shape.token,
        border = border.token,
        contentStyle = content.build(),
    )
}
