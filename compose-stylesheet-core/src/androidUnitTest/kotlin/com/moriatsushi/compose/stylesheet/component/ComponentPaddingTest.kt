package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.layout.PaddingValues
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
import com.moriatsushi.compose.stylesheet.token.Token
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentPaddingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPadding() {
        val style = DummyComponentStyle {
            padding += 20.dp
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun testPadding_token() {
        val token = Token("size", 20.dp)
        val style = DummyComponentStyle {
            padding += token
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun testPadding_paddingValues() {
        val style = DummyComponentStyle {
            padding += PaddingValues(20.dp)
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(50.dp)
            .assertHeightIsEqualTo(50.dp)
    }

    @Test
    fun testPadding_vertical() {
        val style = DummyComponentStyle {
            padding.vertical += 10.dp
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(10.dp)
            .assertHeightIsEqualTo(30.dp)
    }

    @Test
    fun testPadding_horizontal() {
        val style = DummyComponentStyle {
            padding.horizontal += 10.dp
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(30.dp)
            .assertHeightIsEqualTo(10.dp)
    }

    @Test
    fun testPadding_top() {
        val style = DummyComponentStyle {
            padding.top += 10.dp
        }

        composeTestRule.setContent {
            DummyComponent(
                modifier = Modifier.testTag("dummyComponent"),
                style = style,
            )
        }

        composeTestRule.onNodeWithTag("dummyComponent")
            .assertWidthIsEqualTo(10.dp)
            .assertHeightIsEqualTo(20.dp)
    }
}
