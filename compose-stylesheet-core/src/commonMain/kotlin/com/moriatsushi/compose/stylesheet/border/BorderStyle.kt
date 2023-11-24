package com.moriatsushi.compose.stylesheet.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.graphics.BrushStyle
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

@Immutable
sealed interface BorderStyle {
    val width: Token<Dp>
    val brush: Token<BrushStyle>

    companion object {
        val Unspecified: BorderStyle = BorderStyle()
    }
}

@Composable
@StyleSheetComponentApi
fun BorderStyle.asBorderStroke(): BorderStroke? =
    if (this != BorderStyle.Unspecified) {
        BorderStroke(width = width.value, brush = brush.value.asBrush())
    } else {
        null
    }

internal fun BorderStyle.copy(
    width: Token<Dp> = this.width,
    brush: Token<BrushStyle> = this.brush,
): BorderStyle = BorderStyle(width = width, brush = brush)

internal fun BorderStyle(
    width: Token<Dp> = Token(Dp.Unspecified),
    brush: Token<BrushStyle> = Token(BrushStyle.Unspecified),
): BorderStyle = BorderStyleImpl(width = width, brush = brush)

internal fun BorderStyle(borderStroke: BorderStroke): BorderStyle =
    BorderStyle(width = Token(borderStroke.width), brush = Token(BrushStyle(borderStroke.brush)))

@Immutable
private data class BorderStyleImpl(
    override val width: Token<Dp>,
    override val brush: Token<BrushStyle>,
) : BorderStyle
