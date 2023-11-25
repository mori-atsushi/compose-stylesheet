package com.moriatsushi.compose.stylesheet.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    }

    val finalColor = mergedStyle.color?.value ?: Color.Gray
    val finalThickness = mergedStyle.thickness?.value ?: 1.dp

    Canvas(
        modifier = modifier
            .componentCommonStyle(mergedStyle.commonStyle)
            .wrapContentSize()
            .fillMaxWidth()
            .height(finalThickness),
    ) {
        drawLine(
            color = finalColor,
            strokeWidth = finalThickness.toPx(),
            start = Offset(0f, finalThickness.toPx() / 2),
            end = Offset(size.width, finalThickness.toPx() / 2),
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
