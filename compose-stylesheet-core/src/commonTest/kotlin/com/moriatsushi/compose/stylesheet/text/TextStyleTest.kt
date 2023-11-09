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
            Text {
                color += colorToken
            }
        }
        assertEquals(colorToken, styleSheet.getStyle(Text).color)
    }

    @Test
    fun testGetStyle_default() {
        val styleSheet = StyleSheet.Empty
        assertEquals(ColorToken.Unspecified, styleSheet.getStyle(Text).color)
    }

    companion object {
        private val colorToken = ColorToken("color", Color.Red)
    }
}
