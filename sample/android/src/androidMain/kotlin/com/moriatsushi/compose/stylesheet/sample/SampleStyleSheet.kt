package com.moriatsushi.compose.stylesheet.sample

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.text.text
import com.moriatsushi.compose.stylesheet.theme.themeStyleSheet

@Composable
fun styleSheet(
    useDarkMode: Boolean = isSystemInDarkTheme(),
): StyleSheet {
    val default = themeStyleSheet(useDarkMode = useDarkMode)
    return remember(default, useDarkMode) {
        val custom = createStyleSheet(useDarkMode = useDarkMode)
        StyleSheet.merge(default, custom)
    }
}

private fun createStyleSheet(useDarkMode: Boolean): StyleSheet = StyleSheet {
    Colors.subText += if (useDarkMode) Color.LightGray else Color.DarkGray

    text(TextTags.primary) {
        fontSize += 24.sp
    }

    text(TextTags.secondary) {
        fontSize += 14.sp
        color += Colors.subText
    }
}
