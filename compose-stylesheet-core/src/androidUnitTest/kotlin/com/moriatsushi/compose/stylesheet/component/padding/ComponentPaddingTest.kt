package com.moriatsushi.compose.stylesheet.component.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token
import kotlin.test.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(StyleSheetComponentApi::class)
@RunWith(AndroidJUnit4::class)
class ComponentPaddingTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAsPaddingValues() {
        val componentPadding = ComponentPadding(
            start = Token(10.dp),
            top = Token(20.dp),
            end = Token(30.dp),
            bottom = Token(40.dp),
        )
        composeTestRule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 10.dp,
                        top = 20.dp,
                        right = 30.dp,
                        bottom = 40.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 30.dp,
                        top = 20.dp,
                        right = 10.dp,
                        bottom = 40.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }
        }
    }

    @Test
    fun testAsPaddingValues_absolute() {
        val componentPadding = ComponentPadding(
            left = Token(10.dp),
            top = Token(20.dp),
            right = Token(30.dp),
            bottom = Token(40.dp),
        )
        composeTestRule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 10.dp,
                        top = 20.dp,
                        right = 30.dp,
                        bottom = 40.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 10.dp,
                        top = 20.dp,
                        right = 30.dp,
                        bottom = 40.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }
        }
    }

    @Test
    fun testAsPaddingValues_paddingValues() {
        val paddingValues = PaddingValues(
            start = 10.dp,
            top = 20.dp,
            end = 30.dp,
            bottom = 40.dp,
        )
        val componentPadding = ComponentPadding(paddingValues)
        composeTestRule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 10.dp,
                        top = 20.dp,
                        right = 30.dp,
                        bottom = 40.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 30.dp,
                        top = 20.dp,
                        right = 10.dp,
                        bottom = 40.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }
        }
    }

    @Test
    fun testAsPaddingValues_copied() {
        val paddingValues = PaddingValues(
            start = 10.dp,
            top = 20.dp,
            end = 30.dp,
            bottom = 40.dp,
        )
        val componentPadding = ComponentPadding(paddingValues)
            .copy(left = Token(50.dp))
            .copy(bottom = Token(60.dp))
            .copy(start = Token(70.dp))

        composeTestRule.setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 70.dp,
                        top = 20.dp,
                        right = 30.dp,
                        bottom = 60.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                assertEquals(
                    PaddingValues.Absolute(
                        left = 50.dp,
                        top = 20.dp,
                        right = 70.dp,
                        bottom = 60.dp,
                    ),
                    componentPadding.asPaddingValues(),
                )
            }
        }
    }
}
