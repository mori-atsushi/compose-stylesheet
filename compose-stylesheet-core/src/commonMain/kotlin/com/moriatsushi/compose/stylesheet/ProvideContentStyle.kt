package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * Sets the given [contentStyle] as the current content style.
 */
@Composable
fun ProvideContentStyle(
    contentStyle: ContentStyle,
    content: @Composable () -> Unit,
) {
    val mergedContentStyle = LocalContentStyle.current.merge(contentStyle)

    CompositionLocalProvider(
        LocalContentStyle provides mergedContentStyle,
        content = content,
    )
}

val LocalContentStyle = compositionLocalOf { ContentStyle.Default }
