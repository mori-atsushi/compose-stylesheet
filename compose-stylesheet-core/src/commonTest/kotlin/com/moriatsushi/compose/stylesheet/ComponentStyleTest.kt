package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.dummy.dummyComponent
import com.moriatsushi.compose.stylesheet.tag.Tag
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentStyleTest {
    @Test
    fun testGetStyle() {
        val styleSheet = StyleSheet {
            dummyComponent {
                color1 += colorToken1
            }
            dummyComponent(tag1) {
                color1 += colorToken2
            }
            dummyComponent(tag2) {
                color1 += colorToken3
            }
        }

        val style = styleSheet.getStyle(dummyComponent)
        assertEquals(colorToken1, style.color1)

        val tag1Style = styleSheet.getStyle(dummyComponent, tag1)
        assertEquals(colorToken2, tag1Style.color1)

        val tag2Style = styleSheet.getStyle(dummyComponent, tag2)
        assertEquals(colorToken3, tag2Style.color1)

        val multiTagStyle = styleSheet.getStyle(dummyComponent, tag1 + tag2)
        assertEquals(colorToken3, multiTagStyle.color1)
    }

    @Test
    fun testGetStyle_override() {
        val styleSheet = StyleSheet {
            dummyComponent {
                color1 += colorToken1
                color2 += colorToken1
            }
            dummyComponent(tag1) {
                color2 += colorToken2
            }
        }

        val style = styleSheet.getStyle(dummyComponent, tag1)
        assertEquals(colorToken1, style.color1)
        assertEquals(colorToken2, style.color2)
    }

    @Test
    fun testGetStyle_commonStyle() {
        val styleSheet = StyleSheet {
            dummyComponent {
                background += colorToken1
            }
        }

        val style = styleSheet.getStyle(dummyComponent)
        assertEquals(colorToken1, style.commonStyle.background)
    }

    companion object {
        private val colorToken1 = Token("color1", Color.Black)
        private val colorToken2 = Token("color2", Color.Red)
        private val colorToken3 = Token("color3", Color.Blue)

        private val tag1 = Tag("tag1", dummyComponent)
        private val tag2 = Tag("tag2", dummyComponent)
    }
}
