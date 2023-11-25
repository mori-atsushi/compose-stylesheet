package com.moriatsushi.compose.stylesheet.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * A divider which is a thin line. It is used to separate content.
 */
@Composable
fun Divider(
    modifier: Modifier = Modifier,
    thickness: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
    orientation: DividerOrientation? = null,
    tags: TagModifier<DividerStyle> = TagModifier(),
    dividerStyle: DividerStyle = DividerStyle.Default,
) {
    val localStyle = StyleSheet.getStyle(divider, tags)
    val mergedStyle = DividerStyle {
        this += localStyle
        this += dividerStyle

        if (thickness.isSpecified) {
            this.thickness += thickness
        }
        if (color.isSpecified) {
            this.color += color
        }
        this.orientation += orientation
    }

    val finalColor = mergedStyle.color?.value ?: Color.Gray
    val finalThickness = mergedStyle.thickness?.value ?: 1.dp
    val finalOrientation = mergedStyle.orientation ?: DividerOrientation.Horizontal
    val dividerModifier = modifier
        .componentCommonStyle(mergedStyle.commonStyle)
        .wrapContentSize()

    when (finalOrientation) {
        DividerOrientation.Horizontal ->
            HorizontalDivider(
                modifier = dividerModifier,
                thickness = finalThickness,
                color = finalColor,
            )

        DividerOrientation.Vertical ->
            VerticalDivider(
                modifier = dividerModifier,
                thickness = finalThickness,
                color = finalColor,
            )
    }
}

@Composable
private fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness),
    ) {
        drawLine(
            color = color,
            strokeWidth = thickness.toPx(),
            start = Offset(0f, thickness.toPx() / 2),
            end = Offset(size.width, thickness.toPx() / 2),
        )
    }
}

@Composable
private fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified,
) {
    Canvas(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness),
    ) {
        drawLine(
            color = color,
            strokeWidth = thickness.toPx(),
            start = Offset(thickness.toPx() / 2, 0f),
            end = Offset(thickness.toPx() / 2, size.height),
        )
    }
}

/**
 * An object for [Divider].
 */
object Divider

/**
 * A symbol for [Divider].
 */
val divider = Component(
    name = "Divider",
    defaultStyle = DividerStyle.Default,
    createBuilder = ::DividerStyleBuilder,
)
