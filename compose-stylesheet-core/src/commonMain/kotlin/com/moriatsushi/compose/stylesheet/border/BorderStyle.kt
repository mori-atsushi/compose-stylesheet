package com.moriatsushi.compose.stylesheet.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

sealed interface BorderStyle {
    val width: Token<Dp>
    val color: Token<Color>
}

@Composable
@StyleSheetComponentApi
fun BorderStyle.asBorderStroke(): BorderStroke = BorderStroke(
    width = width.value,
    color = color.value,
)

fun BorderStyle(
    width: Token<Dp> = Token(0.dp),
    color: Token<Color> = Token(Color.Transparent),
): BorderStyle = BorderStyleImpl(
    width = width,
    color = color,
)

private data class BorderStyleImpl(
    override val width: Token<Dp>,
    override val color: Token<Color>,
) : BorderStyle
