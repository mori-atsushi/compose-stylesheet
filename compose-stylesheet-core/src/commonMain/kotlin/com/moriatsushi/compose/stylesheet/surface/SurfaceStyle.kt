package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.ComponentStyle
import com.moriatsushi.compose.stylesheet.ContentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Surface].
 */
@Stable
interface SurfaceStyle : ComponentStyle {
    val background: Token<Color>?
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
    contentStyle: ContentStyle = ContentStyle.Default,
): SurfaceStyle = SurfaceStyleImpl(
    background = background,
    contentStyle = contentStyle,
)

@Immutable
private data class SurfaceStyleImpl(
    override val background: Token<Color>?,
    override val contentStyle: ContentStyle,
) : SurfaceStyle
