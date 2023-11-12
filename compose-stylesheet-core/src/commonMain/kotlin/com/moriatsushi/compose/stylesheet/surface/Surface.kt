package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ContentStyle
import com.moriatsushi.compose.stylesheet.LocalContentStyle
import com.moriatsushi.compose.stylesheet.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An element that draws a [backgroundColor] behind its [content].
 *
 * The surface style is applied in the following order:
 *
 * 1. The specified arguments to this function, such as [backgroundColor], etc.
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
    backgroundColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    surfaceStyle: SurfaceStyle = SurfaceStyle.Default,
    content: @Composable () -> Unit,
) {
    val localStyle = StyleSheet.getStyle(surface, tags)
    val mergedStyle = SurfaceStyle {
        this += localStyle
        this += surfaceStyle

        this.backgroundColor += backgroundColor
        this.content.color += contentColor
    }

    Box(
        modifier = modifier
            .background(mergedStyle.backgroundColor?.value ?: Color.Unspecified),
        propagateMinConstraints = true,
    ) {
        ProvideContentStyle(
            contentStyle = mergedStyle.contentStyle,
            content = content,
        )
    }
}

/**
 * A symbol for [Surface].
 */
val surface = Component(
    name = "Surface",
    defaultStyle = SurfaceStyle(),
    createBuilder = ::SurfaceStyleBuilder,
)
