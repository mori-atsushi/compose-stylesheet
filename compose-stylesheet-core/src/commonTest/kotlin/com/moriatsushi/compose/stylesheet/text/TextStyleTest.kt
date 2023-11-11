package com.moriatsushi.compose.stylesheet.text

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.tag.tag
import kotlin.test.Test
import kotlin.test.assertEquals

class TextStyleTest {
    @Test
    fun testGetStyle() {
        val styleSheet = StyleSheet {
            text {
                color += redColor
            }
        }
        assertEquals(redColor, styleSheet.getStyle(text).color)
    }

    @Test
    fun testGetStyle_default() {
        val styleSheet = StyleSheet.Empty
        assertEquals(ColorToken.Unspecified, styleSheet.getStyle(text).color)
    }

    @Test
    fun testGetStyle_tag() {
        val styleSheet = StyleSheet {
            text {
                color += redColor
            }
            text(tag1) {
                color += blueColor
            }
        }
        assertEquals(redColor, styleSheet.getStyle(text).color)
        assertEquals(blueColor, styleSheet.getStyle(text, tag1).color)
    }

    companion object {
        private val redColor = ColorToken("red", Color.Red)
        private val blueColor = ColorToken("blue", Color.Blue)
        private val tag1 = text.tag("tag1")
    }
}
