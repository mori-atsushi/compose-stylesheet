package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * Sets the given [styleSheet] as the current style sheet.
 */
@Composable
fun ProvideStyleSheet(
    styleSheet: StyleSheet,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalStyleSheet provides styleSheet,
        content = content,
    )
}

internal val LocalStyleSheet = compositionLocalOf { StyleSheet.Empty }