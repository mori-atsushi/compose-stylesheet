package com.moriatsushi.compose.stylesheet.text

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.color.ColorToken
import kotlin.test.Test
import kotlin.test.assertEquals

class TextStyleTest {
    @Test
    fun testGetStyle() {
        val styleSheet = StyleSheet {
            text {
                color += colorToken
            }
        }
        assertEquals(colorToken, styleSheet.getStyle(text).color)
    }

    @Test
    fun testGetStyle_default() {
        val styleSheet = StyleSheet.Empty
        assertEquals(ColorToken.Unspecified, styleSheet.getStyle(text).color)
    }

    companion object {
        private val colorToken = ColorToken("color", Color.Red)
    }
}
