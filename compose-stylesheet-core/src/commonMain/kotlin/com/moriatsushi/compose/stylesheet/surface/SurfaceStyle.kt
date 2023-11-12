package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Surface].
 */
@Stable
interface SurfaceStyle : ComponentStyle {
    val background: Token<Color>?
    val shape: Token<Shape?>?
    val border: Token<BorderStroke?>?
    val contentStyle: ContentStyle

    companion object {
        /**
         * Constant for a default [SurfaceStyle].
         */
        val Default: SurfaceStyle = SurfaceStyle()
    }
}

/**
 * Creates a [SurfaceStyle] using the [builder].
 */
fun SurfaceStyle(builder: SurfaceStyleBuilder.() -> Unit): SurfaceStyle =
    SurfaceStyleBuilder().apply(builder).build()

internal fun SurfaceStyle(
    background: Token<Color>? = null,
    shape: Token<Shape?>? = null,
    border: Token<BorderStroke?>? = null,
    contentStyle: ContentStyle = ContentStyle.Default,
): SurfaceStyle = SurfaceStyleImpl(
    background = background,
    shape = shape,
    border = border,
    contentStyle = contentStyle,
)

@Immutable
private data class SurfaceStyleImpl(
    override val background: Token<Color>?,
    override val shape: Token<Shape?>?,
    override val border: Token<BorderStroke?>?,
    override val contentStyle: ContentStyle,
) : SurfaceStyle
