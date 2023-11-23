package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.compose.stylesheet.dummy.DummyComponent
import com.moriatsushi.compose.stylesheet.dummy.DummyComponentStyle
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentSizeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSize() {
        val style = DummyComponentStyle {
            size += 100.dp
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(100.dp)
            .assertHeightIsEqualTo(100.dp)
    }

    @Test
    fun testSize_fill() {
        val style = DummyComponentStyle {
            size += fill
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(500.dp)
            .assertHeightIsEqualTo(600.dp)
    }

    @Test
    fun testSize_default() {
        composeTestRule
            .dummyComponent(DummyComponentStyle.Default)
            .assertWidthIsEqualTo(10.dp)
            .assertHeightIsEqualTo(10.dp)
    }

    @Test
    fun testMinSize() {
        val style = DummyComponentStyle {
            minSize += 100.dp
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(100.dp)
            .assertHeightIsEqualTo(100.dp)
    }

    @Test
    fun testMaxSize() {
        val style = DummyComponentStyle {
            maxSize += 5.dp
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(5.dp)
            .assertHeightIsEqualTo(5.dp)
    }

    @Test
    fun testWidthHeight() {
        val style = DummyComponentStyle {
            width += 100.dp
            height += 200.dp
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(100.dp)
            .assertHeightIsEqualTo(200.dp)
    }

    @Test
    fun testWidthHeight_fill() {
        val style = DummyComponentStyle {
            width += fill
            height += fill
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(500.dp)
            .assertHeightIsEqualTo(600.dp)
    }

    @Test
    fun testMinWidthHeight() {
        val style = DummyComponentStyle {
            minWidth += 100.dp
            minHeight += 200.dp
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(100.dp)
            .assertHeightIsEqualTo(200.dp)
    }

    @Test
    fun testMaxWidthHeight() {
        val style = DummyComponentStyle {
            maxWidth += 3.dp
            maxHeight += 6.dp
        }

        composeTestRule
            .dummyComponent(style)
            .assertWidthIsEqualTo(3.dp)
            .assertHeightIsEqualTo(6.dp)
    }

    private fun ComposeContentTestRule.dummyComponent(
        style: DummyComponentStyle,
    ): SemanticsNodeInteraction {
        setContent {
            Box(modifier = Modifier.requiredSize(500.dp, 600.dp)) {
                DummyComponent(
                    modifier = Modifier.testTag("dummyComponent"),
                    style = style,
                )
            }
        }

        return onNodeWithTag("dummyComponent")
    }
}
