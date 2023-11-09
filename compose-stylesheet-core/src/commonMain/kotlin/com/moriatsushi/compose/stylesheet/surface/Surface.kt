package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.Component

/**
 * An element that draws a [backgroundColor] behind its [content].
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.background(backgroundColor),
        propagateMinConstraints = true,
    ) { content() }
}

/**
 * Utilities for the [Surface] Composable.
 */
object Surface : Component<SurfaceStyle, SurfaceStyleBuilder> {
    override val defaultStyle: SurfaceStyle = SurfaceStyle()

    override fun createBuilder(): SurfaceStyleBuilder = SurfaceStyleBuilder()
}
