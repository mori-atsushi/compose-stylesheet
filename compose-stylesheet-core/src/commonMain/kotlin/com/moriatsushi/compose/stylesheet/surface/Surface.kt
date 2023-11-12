package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.content.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An element that draws a [background] behind its [content].
 *
 * The surface style is applied in the following order:
 *
 * 1. The specified arguments to this function, such as [background], [shape], etc.
 * 2. The specified [surfaceStyle] argument to this function.
 * 3. Styles specified by [tags].
 * 4. The current [SurfaceStyle] from [StyleSheet].
 *
 * The content style is applied in the following order:
 *
 * 1. The specified arguments to this function, such as [contentColor], etc.
 * 2. The [ContentStyle] in the specified [surfaceStyle] argument to this function.
 * 3. The [ContentStyle] in [SurfaceStyle]s specified by [tags].
 * 4. The [ContentStyle] in the current [SurfaceStyle] from [StyleSheet].
 * 5. The current [ContentStyle] from [LocalContentStyle].
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    tags: TagModifier<SurfaceStyle> = TagModifier(),
    background: Color = Color.Unspecified,
    shape: Shape? = null,
    border: BorderStroke? = null,
    contentColor: Color = Color.Unspecified,
    surfaceStyle: SurfaceStyle = SurfaceStyle.Default,
    content: @Composable () -> Unit,
) {
    val localStyle = StyleSheet.getStyle(surface, tags)
    val mergedStyle = SurfaceStyle {
        this += localStyle
        this += surfaceStyle

        this.background += background
        this.shape += shape
        this.border += border
        this.content.color += contentColor
    }

    Box(
        modifier = modifier.surface(
            shape = mergedStyle.shape?.value ?: RectangleShape,
            backgroundColor = mergedStyle.background?.value ?: Color.Unspecified,
            border = mergedStyle.border?.value,
        ),
        propagateMinConstraints = true,
    ) {
        ProvideContentStyle(
            contentStyle = mergedStyle.contentStyle,
            content = content,
        )
    }
}

private fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
): Modifier = this
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)

/**
 * A symbol for [Surface].
 */
val surface = Component(
    name = "Surface",
    defaultStyle = SurfaceStyle(),
    createBuilder = ::SurfaceStyleBuilder,
)
