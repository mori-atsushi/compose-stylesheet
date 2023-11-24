package com.moriatsushi.compose.stylesheet.appbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.content.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

@Composable
fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
    tags: TagModifier<TopAppBarStyle> = TagModifier(),
    topAppBarStyle: TopAppBarStyle = TopAppBarStyle.Default,
    windowInsets: WindowInsets = topAppBarWindowInsets,
) {
    val localStyle = StyleSheet.getStyle(topAppBar, tags)
    val mergedStyle = TopAppBarStyle {
        this += localStyle
        this += topAppBarStyle
    }
    Row(
        modifier = modifier
            .componentCommonStyle(mergedStyle.commonStyle)
            .padding(windowInsets.asPaddingValues()),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProvideContentStyle(mergedStyle.contentStyle) {
            if (navigationIcon != null) {
                HorizontalSpacer(
                    width = mergedStyle.layout.spaceBetweenNavigationIconAndContainer?.value,
                )
                navigationIcon()
                HorizontalSpacer(
                    width = mergedStyle.layout.spaceBetweenNavigationIconAndTitle?.value,
                )
            } else {
                HorizontalSpacer(
                    width = mergedStyle.layout.spaceBetweenTitleAndContainer?.value,
                )
            }
            Row(modifier = Modifier.weight(1F)) {
                title()
            }
            if (actions != null) {
                HorizontalSpacer(
                    width = mergedStyle.layout.spaceBetweenActionsAndContent?.value,
                )
                actions()
                HorizontalSpacer(
                    width = mergedStyle.layout.spaceBetweenActionsAndContainer?.value,
                )
            } else {
                HorizontalSpacer(
                    width = mergedStyle.layout.spaceBetweenTitleAndContainer?.value,
                )
            }
        }
    }
}

@Composable
private fun HorizontalSpacer(width: Dp?) {
    if (width != null && width.isSpecified && width > 0.dp) {
        Spacer(modifier = Modifier.width(width))
    }
}

private val topAppBarWindowInsets: WindowInsets
    @Composable
    get() = WindowInsets.systemBars
        .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top)

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
