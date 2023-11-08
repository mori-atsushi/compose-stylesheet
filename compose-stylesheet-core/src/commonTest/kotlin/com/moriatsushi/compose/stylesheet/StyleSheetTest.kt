package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken
import kotlin.test.Test
import kotlin.test.assertEquals

class StyleSheetTest {
    @Test
    fun testGetColor_default() {
        val styleSheet = StyleSheet()

        assertEquals(Color.Black, styleSheet.getColor(color1))
        assertEquals(Color.Red, styleSheet.getColor(color2))
        assertEquals(Color.Blue, styleSheet.getColor(color3))
    }

    @Test
    fun testGetColor_override() {
        val styleSheet = StyleSheet(
            colors = mapOf(
                color1 to color2,
                color2 to color3,
            ),
        )

        assertEquals(Color.Blue, styleSheet.getColor(color1))
        assertEquals(Color.Blue, styleSheet.getColor(color2))
        assertEquals(Color.Blue, styleSheet.getColor(color3))
    }

    companion object {
        private val color1 = ColorToken("color1", Color.Black)
        private val color2 = ColorToken("color2", Color.Red)
        private val color3 = ColorToken("color3", Color.Blue)
    }
}
