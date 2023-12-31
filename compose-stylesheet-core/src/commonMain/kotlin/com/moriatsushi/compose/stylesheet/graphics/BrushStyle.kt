package com.moriatsushi.compose.stylesheet.graphics

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

sealed interface BrushStyle {
    @StyleSheetComponentApi
    @Composable
    fun asBrush(): Brush

    companion object {
        val Unspecified: BrushStyle = BrushStyle(Color.Unspecified)
    }
}

fun BrushStyle(color: Token<Color>): BrushStyle = SolidColorBrush(color = color)

fun BrushStyle(color: Color): BrushStyle = BrushStyle(color = Token(color))

fun BrushStyle(brush: Brush): BrushStyle = BrushHolder(brush = brush)

private data class SolidColorBrush(val color: Token<Color>) : BrushStyle {
    @Composable
    override fun asBrush(): Brush = SolidColor(color.value)
}

private data class BrushHolder(private val brush: Brush) : BrushStyle {
    @Composable
    override fun asBrush(): Brush = brush
}
