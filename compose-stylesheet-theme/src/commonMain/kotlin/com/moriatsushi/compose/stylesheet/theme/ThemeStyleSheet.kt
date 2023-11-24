package com.moriatsushi.compose.stylesheet.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.theme.button.buttonStyleSheet
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
        Colors.semantic.inverseSurface += if (useDarkMode) Colors.white else Colors.gray900
        Colors.semantic.inverseSurfacePressed += if (useDarkMode) Colors.gray300 else Colors.gray600
        Colors.semantic.onInverseSurface += if (useDarkMode) Colors.gray900 else Colors.white

        Colors.semantic.disabledSurface += if (useDarkMode) Colors.gray700 else Colors.gray100
        Colors.semantic.onDisabledSurface += if (useDarkMode) Colors.gray500 else Colors.gray300

        Colors.semantic.focus += if (useDarkMode) Colors.blue500 else Colors.blue300

        content {
            color += Colors.semantic.onBackground
        }

        this += buttonStyleSheet
        this += surfaceStyleSheet
    }
}
