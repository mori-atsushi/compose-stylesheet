package com.moriatsushi.compose.stylesheet.text

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TextStyleTest {
    @Test
    fun testGetStyle() {
        val textStyle = TextStyle {
            color += redColor
        }
        assertEquals(redColor, textStyle.color)
    }

    @Test
    fun testGetStyle_default() {
        val textStyle = TextStyle.Default
        assertNull(textStyle.color)
    }

    companion object {
        private val redColor = Token("red", Color.Red)
    }
}
