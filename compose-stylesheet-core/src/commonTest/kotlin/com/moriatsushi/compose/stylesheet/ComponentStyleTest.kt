package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorSetter
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.tag.Tag
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentStyleTest {
    @Test
    fun testGetStyle() {
        val styleSheet = StyleSheet {
            sampleComponent {
                color1 += colorToken1
            }
            sampleComponent(tag1) {
                color1 += colorToken2
            }
            sampleComponent(tag2) {
                color1 += colorToken3
            }
        }

        val style = styleSheet.getStyle(sampleComponent)
        assertEquals(colorToken1, style.color1)

        val tag1Style = styleSheet.getStyle(sampleComponent, tag1)
        assertEquals(colorToken2, tag1Style.color1)

        val tag2Style = styleSheet.getStyle(sampleComponent, tag2)
        assertEquals(colorToken3, tag2Style.color1)

        val multiTagStyle = styleSheet.getStyle(sampleComponent, tag1 + tag2)
        assertEquals(colorToken3, multiTagStyle.color1)
    }

    @Test
    fun testGetStyle_override() {
        val styleSheet = StyleSheet {
            sampleComponent {
                color1 += colorToken1
                color2 += colorToken1
            }
            sampleComponent(tag1) {
                color2 += colorToken2
            }
        }

        val style = styleSheet.getStyle(sampleComponent, tag1)
        assertEquals(colorToken1, style.color1)
        assertEquals(colorToken2, style.color2)
    }

    private class SampleComponentStyle(
        val color1: ColorToken = ColorToken.Unspecified,
        val color2: ColorToken = ColorToken.Unspecified,
    ) : ComponentStyle

    private class SampleComponentStyleBuilder : StyleBuilder<SampleComponentStyle> {
        val color1: ColorSetter = ColorSetter()
        val color2: ColorSetter = ColorSetter()

        override fun plusAssign(other: SampleComponentStyle) {
            color1 += other.color1
            color2 += other.color2
        }

        override fun build(): SampleComponentStyle = SampleComponentStyle(
            color1 = color1.value,
            color2 = color2.value,
        )
    }

    companion object {
        private val colorToken1 = ColorToken("color1", Color.Black)
        private val colorToken2 = ColorToken("color2", Color.Red)
        private val colorToken3 = ColorToken("color3", Color.Blue)

        private val sampleComponent = Component(
            name = "SampleComponent",
            defaultStyle = SampleComponentStyle(),
            createBuilder = ::SampleComponentStyleBuilder,
        )

        private val tag1 = Tag("tag1", sampleComponent)
        private val tag2 = Tag("tag2", sampleComponent)
    }
}
