package com.moriatsushi.compose.stylesheet.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An element that draws a [backgroundColor] behind its [content].
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
