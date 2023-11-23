package com.moriatsushi.compose.stylesheet.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toolingGraphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.content.LocalContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An element that displays an icon from the [imageVector].
 */
@Composable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tags: TagModifier<IconStyle> = TagModifier(),
    tint: Color = Color.Unspecified,
    iconStyle: IconStyle = IconStyle.Default,
) {
    Icon(
        painter = rememberVectorPainter(imageVector),
        contentDescription = contentDescription,
        modifier = modifier,
        tags = tags,
        tint = tint,
        iconStyle = iconStyle,
    )
}

/**
 * An element that displays an icon from the [bitmap].
 */
@Composable
fun Icon(
    bitmap: ImageBitmap,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tags: TagModifier<IconStyle> = TagModifier(),
    tint: Color = Color.Unspecified,
    iconStyle: IconStyle = IconStyle.Default,
) {
    val painter = remember(bitmap) { BitmapPainter(bitmap) }
    Icon(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        tags = tags,
        tint = tint,
        iconStyle = iconStyle,
    )
}

/**
 * An element that displays an icon from the [painter].
 */
@Composable
fun Icon(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tags: TagModifier<IconStyle> = TagModifier(),
    tint: Color = Color.Unspecified,
    iconStyle: IconStyle = IconStyle.Default,
) {
    val localIconStyle = StyleSheet.getStyle(icon, tags)
    val localContentStyle = LocalContentStyle.current

    val mergedStyle = IconStyle {
        color += localContentStyle.color
        this += localIconStyle
        this += iconStyle

        if (tint.isSpecified) {
            this.color += tint
        }
    }

    val finalTint = mergedStyle.color?.value?.takeIf { it.isSpecified }
    val colorFilter = remember(finalTint) { finalTint?.let(ColorFilter::tint) }

    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }

    Box(
        modifier
            .componentCommonStyle(mergedStyle.commonStyle)
            .toolingGraphicsLayer()
            .paint(painter, colorFilter = colorFilter, contentScale = ContentScale.Fit)
            .then(semantics),
    )
}

/**
 * A symbol for [Icon].
 */
val icon = Component(
    name = "Text",
    defaultStyle = IconStyle(),
    createBuilder = ::IconStyleBuilder,
)
