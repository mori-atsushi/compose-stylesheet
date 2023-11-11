package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

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

        val actual = styleSheet.contentStyle.color?.let(styleSheet::getValue)
        assertEquals(Color.Red, actual)
    }

    @Test
    fun testColor_empty() {
        val styleSheet = StyleSheet.Empty

        val actual = styleSheet.contentStyle.color?.let(styleSheet::getValue)
        assertNull(actual)
    }

    @Test
    fun testColor_setToken() {
        val styleSheet = StyleSheet {
            colorToken1 += Color.Red

            content { color += colorToken1 }
        }

        val actual = styleSheet.contentStyle.color?.let(styleSheet::getValue)
        assertEquals(Color.Red, actual)
    }

    companion object {
        private val colorToken1 = Token("colorToken1", Color.Black)
        private val colorToken2 = Token("colorToken1", Color.Red)
        private val colorToken3 = Token("colorToken1", Color.Blue)
    }
}
