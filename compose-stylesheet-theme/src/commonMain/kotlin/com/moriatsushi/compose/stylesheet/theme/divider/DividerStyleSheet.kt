package com.moriatsushi.compose.stylesheet.theme.divider

import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.divider.divider
import com.moriatsushi.compose.stylesheet.theme.Colors

internal val dividerStyleSheet = StyleSheet {
    divider {
        color += Colors.semantic.divider
    }
}
