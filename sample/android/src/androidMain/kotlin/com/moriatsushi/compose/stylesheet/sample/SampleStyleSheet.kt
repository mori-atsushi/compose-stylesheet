package com.moriatsushi.compose.stylesheet.sample

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.surface.surface
import com.moriatsushi.compose.stylesheet.text.text

fun createStyleSheet(
    useDarkMode: Boolean,
): StyleSheet = StyleSheet {
    Colors.text += if (useDarkMode) Color.White else Color.Black
    Colors.subText += if (useDarkMode) Color.LightGray else Color.DarkGray
    Colors.background += if (useDarkMode) Color.Black else Color.White

    content {
        color += Colors.text
    }

    surface {
        background += Colors.background
    }

    text(TextTags.primary) {
        fontSize += 24.sp
    }

    text(TextTags.secondary) {
        fontSize += 14.sp
        color += Colors.subText
    }
}
