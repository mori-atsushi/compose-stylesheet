package com.moriatsushi.compose.stylesheet.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.content.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier

@Composable
fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    tags: TagModifier<TopAppBarStyle> = TagModifier(),
    topAppBarStyle: TopAppBarStyle = TopAppBarStyle.Default,
) {
    val localStyle = StyleSheet.getStyle(topAppBar, tags)
    val mergedStyle = TopAppBarStyle {
        this += localStyle
        this += topAppBarStyle
    }
    Row(
        modifier = modifier.componentCommonStyle(mergedStyle.commonStyle),
    ) {
        ProvideContentStyle(mergedStyle.contentStyle) {
            navigationIcon()
            title()
            actions()
        }
    }
}

/**
 * An object for [TopAppBar].
 */
object TopAppBar

/**
 * A symbol for [TopAppBar].
 */
val topAppBar = Component(
    name = "TopAppBar",
    defaultStyle = TopAppBarStyle.Default,
    createBuilder = ::TopAppBarStyleBuilder,
)
