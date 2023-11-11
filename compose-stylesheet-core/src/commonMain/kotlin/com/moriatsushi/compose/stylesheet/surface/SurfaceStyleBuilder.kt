package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.ui.graphics.Color
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
    val backgroundColor: TokenSetter<Color> = TokenSetter()

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
        backgroundColor += other.backgroundColor
        content += other.contentStyle
    }

    override fun build(): SurfaceStyle = SurfaceStyle(
        backgroundColor = backgroundColor.token,
        contentStyle = content.build(),
    )
}
