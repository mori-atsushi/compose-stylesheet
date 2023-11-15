package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.moriatsushi.compose.stylesheet.dummy.dummyComponent
import com.moriatsushi.compose.stylesheet.tag.Tag
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StyleSheetTest {
    @Test
    fun testMerge_token() {
        val styleSheet1 = StyleSheet {
            colorToken1 += Color.Red
            colorToken2 += Color.Green
        }
        val styleSheet2 = StyleSheet {
            colorToken2 += Color.Blue
            colorToken3 += Color.Yellow
        }

        val merged = StyleSheet.merge(styleSheet1, styleSheet2)

        assertEquals(Color.Red, merged.getValue(colorToken1))
        assertEquals(Color.Blue, merged.getValue(colorToken2))
        assertEquals(Color.Yellow, merged.getValue(colorToken3))
    }

    @Test
    fun testMerge_content() {
        val styleSheet1 = StyleSheet {
            content {
                color += Color.Red
                fontSize += 12.sp
            }
        }
        val styleSheet2 = StyleSheet {
            content {
                color += Color.Blue
                fontWeight += FontWeight.Bold
            }
        }

        val merged = StyleSheet.merge(styleSheet1, styleSheet2)

        assertEquals(Color.Blue, merged.contentStyle.color?.let(merged::getValue))
        assertEquals(12.sp, merged.contentStyle.fontSize?.let(merged::getValue))
        assertEquals(FontWeight.Bold, merged.contentStyle.fontWeight?.let(merged::getValue))
    }

    @Test
    fun testMerge_component() {
        val styleSheet1 = StyleSheet {
            dummyComponent {
                color1 += Color.Red
                color2 += Color.Green
            }
            dummyComponent(tag) {
                color1 += Color.Green
                color2 += Color.Blue
            }
        }
        val styleSheet2 = StyleSheet {
            dummyComponent {
                color2 += Color.Blue
                color3 += Color.Yellow
            }
            dummyComponent(tag) {
                color2 += Color.Yellow
                color3 += Color.Red
            }
        }
        val merged = StyleSheet.merge(styleSheet1, styleSheet2)

        assertEquals(Color.Red, merged.getStyle(dummyComponent).color1?.let(merged::getValue))
        assertEquals(Color.Blue, merged.getStyle(dummyComponent).color2?.let(merged::getValue))
        assertEquals(Color.Yellow, merged.getStyle(dummyComponent).color3?.let(merged::getValue))

        assertEquals(
            Color.Green,
            merged.getStyle(dummyComponent, tag).color1?.let(merged::getValue),
        )
        assertEquals(
            Color.Yellow,
            merged.getStyle(dummyComponent, tag).color2?.let(merged::getValue),
        )
        assertEquals(
            Color.Red,
            merged.getStyle(dummyComponent, tag).color3?.let(merged::getValue),
        )
    }

    @Test
    fun testGetValue() {
        val styleSheet = StyleSheet {
            colorToken2 += colorToken1
            colorToken3 += Color.Green
        }

        assertEquals(Color.Black, styleSheet.getValue(colorToken1))
        assertEquals(Color.Black, styleSheet.getValue(colorToken2))
        assertEquals(Color.Green, styleSheet.getValue(colorToken3))
    }

    @Test
    fun testGetValue_empty() {
        val styleSheet = StyleSheet.Empty

        assertEquals(Color.Black, styleSheet.getValue(colorToken1))
        assertEquals(Color.Red, styleSheet.getValue(colorToken2))
        assertEquals(Color.Blue, styleSheet.getValue(colorToken3))
    }

    @Test
    fun testColor() {
        val styleSheet = StyleSheet {
            content { color += Color.Red }
        }

        val actual = styleSheet.contentStyle.color?.let(styleSheet::getValue)
        assertEquals(Color.Red, actual)
    }

    @Test
    fun testColor_empty() {
        val styleSheet = StyleSheet.Empty

        val actual = styleSheet.contentStyle.color?.let(styleSheet::getValue)
        assertNull(actual)
    }

    @Test
    fun testColor_setToken() {
        val styleSheet = StyleSheet {
            colorToken1 += Color.Red

            content { color += colorToken1 }
        }

        val actual = styleSheet.contentStyle.color?.let(styleSheet::getValue)
        assertEquals(Color.Red, actual)
    }

    companion object {
        private val colorToken1 = Token("colorToken1", Color.Black)
        private val colorToken2 = Token("colorToken1", Color.Red)
        private val colorToken3 = Token("colorToken1", Color.Blue)
        private val tag = Tag("tag", dummyComponent)
    }
}
