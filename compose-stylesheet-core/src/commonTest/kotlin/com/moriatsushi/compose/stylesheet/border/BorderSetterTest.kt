package com.moriatsushi.compose.stylesheet.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.graphics.BrushStyle
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals

class BorderSetterTest {
    @Test
    fun testWidth() {
        val setter = BorderSetter()
        setter.width += 10.dp
        assertEquals(BorderStyle(width = Token(10.dp)), setter.value)
    }

    @Test
    fun testWidth_override() {
        val setter = BorderSetter()
        setter += BorderStyle(width = Token(10.dp))
        setter.width += 20.dp
        assertEquals(BorderStyle(width = Token(20.dp)), setter.value)
    }

    @Test
    fun testBrush() {
        val setter = BorderSetter()
        setter.brush += BrushStyle(Color.Black)
        assertEquals(
            BorderStyle(brush = Token(BrushStyle(Color.Black))),
            setter.value,
        )
    }

    @Test
    fun testBrush_override() {
        val setter = BorderSetter()
        setter += BorderStyle(brush = Token(BrushStyle(Color.Black)))
        setter.brush += BrushStyle(Color.Red)
        assertEquals(
            BorderStyle(brush = Token(BrushStyle(Color.Red))),
            setter.value,
        )
    }

    @Test
    fun testColor() {
        val setter = BorderSetter()
        setter.color += Color.Black
        assertEquals(
            BorderStyle(brush = Token(BrushStyle(Color.Black))),
            setter.value,
        )
    }

    @Test
    fun testColor_override() {
        val setter = BorderSetter()
        setter += BorderStyle(brush = Token(BrushStyle(Color.Black)))
        setter.color += Color.Red
        assertEquals(
            BorderStyle(brush = Token(BrushStyle(Color.Red))),
            setter.value,
        )
    }

    @Test
    fun testBorderStyle() {
        val setter = BorderSetter()
        setter += BorderStroke(1.dp, Color.Black)
        assertEquals(
            BorderStyle(width = Token(1.dp), brush = Token(BrushStyle(SolidColor(Color.Black)))),
            setter.value,
        )
    }
}
