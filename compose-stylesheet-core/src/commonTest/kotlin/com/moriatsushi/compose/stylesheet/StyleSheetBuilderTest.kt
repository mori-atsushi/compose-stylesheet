package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken
import kotlin.test.Test
import kotlin.test.assertEquals

class StyleSheetBuilderTest {
    @Test
    fun test() {
        val styleSheet = StyleSheet {
            colors {
                color2 to color1
                color3 to Color.Green
            }
        }

        assertEquals(Color.Black, styleSheet.getColor(color1))
        assertEquals(Color.Black, styleSheet.getColor(color2))
        assertEquals(Color.Green, styleSheet.getColor(color3))
    }

    companion object {
        private val color1 = ColorToken("color1", Color.Black)
        private val color2 = ColorToken("color2", Color.Red)
        private val color3 = ColorToken("color3", Color.Blue)
    }
}
