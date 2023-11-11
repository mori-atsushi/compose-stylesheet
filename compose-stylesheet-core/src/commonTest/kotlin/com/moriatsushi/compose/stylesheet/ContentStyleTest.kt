package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentStyleTest {
    @Test
    fun testMerge() {
        val style1 = ContentStyle {
            color += color1
        }
        val style2 = ContentStyle {
            color += color2
        }
        val mergedStyle = style1.merge(style2)
        assertEquals(color2, mergedStyle.color)
    }

    @Test
    fun testMerge_unspecified() {
        val style1 = ContentStyle {
            color += color1
        }
        val style2 = ContentStyle {
            color += Color.Unspecified
        }
        val mergedStyle = style1.merge(style2)
        assertEquals(color1, mergedStyle.color)
    }

    companion object {
        private val color1 = Token("color1", Color.Red)
        private val color2 = Token("color2", Color.Blue)
    }
}
