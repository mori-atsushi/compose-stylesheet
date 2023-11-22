package com.moriatsushi.compose.stylesheet.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.theme.surface.surfaceStyleSheet

/**
 * Creates a [StyleSheet] for the default theme.
 *
 * @param useDarkMode Whether to use dark mode. If not specified, the system setting is used.
 */
@Composable
fun themeStyleSheet(
    useDarkMode: Boolean = isSystemInDarkTheme(),
): StyleSheet = remember(useDarkMode) {
    StyleSheet {
        Colors.semantic.background += if (useDarkMode) Colors.gray900 else Colors.white
        Colors.semantic.onBackground += if (useDarkMode) Colors.white else Colors.gray900
        Colors.semantic.primarySurface += if (useDarkMode) Colors.gray800 else Colors.gray100
        Colors.semantic.onPrimarySurface += Colors.semantic.onBackground

        content {
            color += Colors.semantic.onBackground
        }

        this += surfaceStyleSheet
    }
}
