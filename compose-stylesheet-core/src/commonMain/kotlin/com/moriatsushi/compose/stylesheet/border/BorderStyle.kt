package com.moriatsushi.compose.stylesheet.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
        val Unspecified: BorderStyle = BorderStyle(
            width = Dp.Unspecified,
            color = Color.Unspecified,
        )
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

fun BorderStyle(width: Dp, brush: BrushStyle): BorderStyle =
    BorderStyleImpl(width = Token(width), brush = Token(brush))

fun BorderStyle(width: Dp, brush: Brush): BorderStyle =
    BorderStyle(width = width, brush = BrushStyle(brush))

fun BorderStyle(width: Token<Dp>, color: Token<Color>): BorderStyle =
    BorderStyleImpl(width = width, brush = Token(BrushStyle(color)))

fun BorderStyle(width: Dp, color: Color): BorderStyle =
    BorderStyle(width = Token(width), color = Token(color))

@Immutable
private data class BorderStyleImpl(
    override val width: Token<Dp>,
    override val brush: Token<BrushStyle>,
) : BorderStyle
