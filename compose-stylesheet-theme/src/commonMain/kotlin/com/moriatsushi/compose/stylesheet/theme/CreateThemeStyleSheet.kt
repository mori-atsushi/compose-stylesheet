package com.moriatsushi.compose.stylesheet.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.compose.stylesheet.StyleSheet

/**
 * Creates a [StyleSheet] for the default theme.
 *
 * @param useDarkMode Whether to use dark mode. If not specified, the system setting is used.
 */
@Composable
fun createThemeStyleSheet(
    useDarkMode: Boolean = isSystemInDarkTheme(),
): StyleSheet = remember(useDarkMode) {
    StyleSheet {

    }
}
