package com.moriatsushi.compose.stylesheet.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.surface.surface

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
        Colors.semantic.primarySurface += if (useDarkMode) Colors.black else Colors.white
        Colors.semantic.onPrimarySurface += if (useDarkMode) Colors.white else Colors.black

        content {
            color += Colors.semantic.onPrimarySurface
        }

        surface {
            background += Colors.semantic.primarySurface
            content.color += Colors.semantic.onPrimarySurface
        }
    }
}
