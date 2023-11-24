package com.moriatsushi.compose.stylesheet.theme.appbar

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.appbar.topAppBar
import com.moriatsushi.compose.stylesheet.theme.Colors

val topAppBarStyleSheet = StyleSheet {
    topAppBar {
        width += fill
        minHeight += 64.dp
        padding.vertical += 2.dp
        background += Colors.semantic.background

        layout {
            spaceBetweenTitleAndContainer += 16.dp
            spaceBetweenNavigationIconAndContainer += 8.dp
            spaceBetweenNavigationIconAndTitle += 8.dp
            spaceBetweenActionsAndContent += 8.dp
            spaceBetweenActionsAndContainer += 8.dp
        }

        content {
            fontSize += 20.sp
            color += Colors.semantic.onBackground
        }
    }
}
