package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertWidthIsEqualTo
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

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(100.dp)
            .assertHeightIsEqualTo(100.dp)
    }

    @Test
    fun testSize_fill() {
        val style = DummyComponentStyle {
            size += fill
        }

        composeTestRule.setContent {
            Box(modifier = Modifier.size(200.dp, 300.dp)) {
                DummyComponent(
                    modifier = Modifier.testTag("dummyComponent"),
                    style = style,
                )
            }
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(200.dp)
            .assertHeightIsEqualTo(300.dp)
    }

    @Test
    fun testSize_default() {
        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(0.dp)
            .assertHeightIsEqualTo(0.dp)
    }

    @Test
    fun testWidthHeight() {
        val style = DummyComponentStyle {
            width += 100.dp
            height += 200.dp
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(100.dp)
            .assertHeightIsEqualTo(200.dp)
    }

    @Test
    fun testWidthHeight_fill() {
        val style = DummyComponentStyle {
            width += fill
            height += fill
        }

        composeTestRule.setContent {
            Box(modifier = Modifier.size(200.dp, 300.dp)) {
                DummyComponent(
                    modifier = Modifier.testTag("dummyComponent"),
                    style = style,
                )
            }
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(200.dp)
            .assertHeightIsEqualTo(300.dp)
    }
}
