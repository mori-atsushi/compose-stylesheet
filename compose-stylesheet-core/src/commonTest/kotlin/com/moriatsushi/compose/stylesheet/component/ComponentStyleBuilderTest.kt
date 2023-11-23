package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.dummy.DummyComponentStyle
import kotlin.test.Test
import kotlin.test.assertEquals

class ComponentStyleBuilderTest {
    @Test
    fun testMerge() {
        val otherStyle = DummyComponentStyle {
            background += Color.Green
            shape += RoundedCornerShape(10.dp)
        }
        val mergedStyle = DummyComponentStyle {
            this += dummyStyle
            this += otherStyle
        }
        val expected = DummyComponentStyle {
            size += 100.dp
            background += Color.Green
            shape += RoundedCornerShape(10.dp)
            border += BorderStroke(1.dp, Color.Black)
        }
        assertEquals(expected, mergedStyle)
    }

    @Test
    fun testMerge_withDefault() {
        val mergedStyle = DummyComponentStyle {
            this += dummyStyle
            this += DummyComponentStyle.Default
        }
        assertEquals(dummyStyle, mergedStyle)
    }

    companion object {
        private val dummyStyle = DummyComponentStyle {
            size += 100.dp
            background += Color.Red
            shape += CircleShape
            border += BorderStroke(1.dp, Color.Black)
        }
    }
}
