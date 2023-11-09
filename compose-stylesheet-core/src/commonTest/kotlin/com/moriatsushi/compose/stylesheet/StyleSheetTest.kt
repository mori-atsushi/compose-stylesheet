package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken
import kotlin.test.Test
import kotlin.test.assertEquals

class StyleSheetTest {
    @Test
    fun testColors() {
        val styleSheet = StyleSheet {
            colors {
                color2 += color1
                color3 += Color.Green
            }
        }

        assertEquals(Color.Black, styleSheet.getColor(color1))
        assertEquals(Color.Black, styleSheet.getColor(color2))
        assertEquals(Color.Green, styleSheet.getColor(color3))
    }

    @Test
    fun testColors_empty() {
        val styleSheet = StyleSheet.Empty

        assertEquals(Color.Black, styleSheet.getColor(color1))
        assertEquals(Color.Red, styleSheet.getColor(color2))
        assertEquals(Color.Blue, styleSheet.getColor(color3))
    }

    @Test
    fun testColor() {
        val styleSheet = StyleSheet {
            content { color += Color.Red }
        }

        assertEquals(Color.Red, styleSheet.color)
    }

    @Test
    fun testColor_empty() {
        val styleSheet = StyleSheet.Empty

        assertEquals(Color.Unspecified, styleSheet.color)
    }

    @Test
    fun testColor_setToken() {
        val styleSheet = StyleSheet {
            colors { color1 += Color.Red }
            content { color += color1 }
        }

        assertEquals(Color.Red, styleSheet.color)
    }

    companion object {
        private val color1 = ColorToken("color1", Color.Black)
        private val color2 = ColorToken("color2", Color.Red)
        private val color3 = ColorToken("color3", Color.Blue)
    }
}
