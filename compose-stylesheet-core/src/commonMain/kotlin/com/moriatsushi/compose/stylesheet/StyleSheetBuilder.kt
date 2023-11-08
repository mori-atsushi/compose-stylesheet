package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.color.ColorsBuilder

/**
 * A builder for [StyleSheet].
 */
@StyleSheetBuilderMarker
class StyleSheetBuilder internal constructor() {
    private val colorsBuilder = ColorsBuilder()
    private val commonBuilder = CommonStyleBuilder()

    /**
     * Defines colors.
     */
    fun colors(builder: ColorsBuilder.() -> Unit) {
        colorsBuilder.builder()
    }

    /**
     * Defines common styles.
     */
    fun common(builder: CommonStyleBuilder.() -> Unit) {
        commonBuilder.builder()
    }

    internal fun build(): StyleSheet = StyleSheet(
        colors = colorsBuilder.build(),
        common = commonBuilder.build(),
    )
}
