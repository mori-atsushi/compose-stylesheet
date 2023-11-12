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
        val mergedStyle = ContentStyle {
            this += style1
            this += style2
        }
        assertEquals(color2, mergedStyle.color)
    }

    @Test
    fun testMerge_empty() {
        val style = ContentStyle {
            color += color1
        }
        val mergedStyle = ContentStyle {
            this += style
            this += ContentStyle.Default
        }
        assertEquals(color1, mergedStyle.color)
    }

    companion object {
        private val color1 = Token("color1", Color.Red)
        private val color2 = Token("color2", Color.Blue)
    }
}
