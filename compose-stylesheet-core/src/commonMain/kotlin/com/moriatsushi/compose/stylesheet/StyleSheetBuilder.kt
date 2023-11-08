package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.color.ColorsBuilder

/**
 * A builder for [StyleSheet].
 */
class StyleSheetBuilder internal constructor() {
    private val colorsBuilder = ColorsBuilder()

    /**
     * Defines colors.
     */
    fun colors(builder: ColorsBuilder.() -> Unit) {
        colorsBuilder.builder()
    }

    internal fun build(): StyleSheet = StyleSheet(
        colors = colorsBuilder.build(),
    )
}
