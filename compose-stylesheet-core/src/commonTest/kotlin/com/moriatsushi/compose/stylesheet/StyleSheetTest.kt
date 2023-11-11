package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals

class StyleSheetTest {
    @Test
    fun testGetValue() {
        val styleSheet = StyleSheet {
            colorToken2 += colorToken1
            colorToken3 += Color.Green
        }

        assertEquals(Color.Black, styleSheet.getValue(colorToken1))
        assertEquals(Color.Black, styleSheet.getValue(colorToken2))
        assertEquals(Color.Green, styleSheet.getValue(colorToken3))
    }

    @Test
    fun testGetValue_empty() {
        val styleSheet = StyleSheet.Empty

        assertEquals(Color.Black, styleSheet.getValue(colorToken1))
        assertEquals(Color.Red, styleSheet.getValue(colorToken2))
        assertEquals(Color.Blue, styleSheet.getValue(colorToken3))
    }

    @Test
    fun testColor() {
        val styleSheet = StyleSheet {
            content { color += Color.Red }
        }

        val actual = styleSheet.contentStyle.color.let(styleSheet::getColor)
        assertEquals(Color.Red, actual)
    }

    @Test
    fun testColor_empty() {
        val styleSheet = StyleSheet.Empty

        val actual = styleSheet.contentStyle.color.let(styleSheet::getColor)
        assertEquals(Color.Unspecified, actual)
    }

    @Test
    fun testColor_setToken() {
        val styleSheet = StyleSheet {
            colors { color1 += Color.Red }
            content { color += color1 }
        }

        val actual = styleSheet.contentStyle.color.let(styleSheet::getColor)
        assertEquals(Color.Red, actual)
    }

    companion object {
        private val colorToken1 = Token("colorToken1", Color.Black)
        private val colorToken2 = Token("colorToken1", Color.Red)
        private val colorToken3 = Token("colorToken1", Color.Blue)

        private val color1 = ColorToken("color1", Color.Black)
        private val color2 = ColorToken("color2", Color.Red)
        private val color3 = ColorToken("color3", Color.Blue)
    }
}
