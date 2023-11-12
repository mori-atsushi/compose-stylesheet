package com.moriatsushi.compose.stylesheet.sample

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.surface.surface

fun createStyleSheet(
    useDarkMode: Boolean,
): StyleSheet = StyleSheet {
    Colors.text += if (useDarkMode) Color.White else Color.Black
    Colors.background += if (useDarkMode) Color.Black else Color.White

    content {
        color += Colors.text
    }

    surface {
        background += Colors.background
    }
}
