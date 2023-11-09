package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.color.asColor

/**
 * An element that draws a [backgroundColor] behind its [content].
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Unspecified,
    content: @Composable () -> Unit,
) {
    val style = StyleSheet.getStyle(Surface)
    val surfaceBackgroundColor = backgroundColor
        .takeOrElse { style.backgroundColor?.asColor() ?: Color.Unspecified }

    Box(
        modifier = modifier.background(surfaceBackgroundColor),
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
