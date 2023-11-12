package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle

/**
 * A style for [Surface].
 */
@Stable
interface SurfaceStyle : ComponentStyle {
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
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
): SurfaceStyle = SurfaceStyleImpl(
    commonStyle = commonStyle,
    contentStyle = contentStyle,
)

@Immutable
private data class SurfaceStyleImpl(
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
) : SurfaceStyle
