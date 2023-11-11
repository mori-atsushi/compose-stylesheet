package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.color.ColorsBuilder
import com.moriatsushi.compose.stylesheet.tag.Tag

/**
 * A builder for [StyleSheet].
 */
@StyleSheetBuilderMarker
class StyleSheetBuilder internal constructor() {
    private val colorsBuilder = ColorsBuilder()
    private val commonBuilder = ContentStyleBuilder()
    private val componentStyles = mutableMapOf<Component<*, *>, ComponentStyleDefinition<*>>()

    /**
     * Defines colors.
     */
    fun colors(builder: ColorsBuilder.() -> Unit) {
        colorsBuilder.builder()
    }

    /**
     * Defines content styles.
     */
    fun content(builder: ContentStyleBuilder.() -> Unit) {
        commonBuilder.builder()
    }

    /**
     * Defines a component style.
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <CS : ComponentStyle, SB : StyleBuilder<CS>> Component<CS, SB>.invoke(
        builder: SB.() -> Unit,
    ) {
        val style = createBuilder().apply(builder).build()
        componentStyles[this] = (componentStyles[this] as? ComponentStyleDefinition<CS>)
            ?.updatedCommonStyle(style)
            ?: ComponentStyleDefinition(style)
    }

    /**
     * Defines a component style for the this [Tag].
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <CS : ComponentStyle, SB : StyleBuilder<CS>> Tag<CS, SB>.invoke(
        builder: SB.() -> Unit,
    ) {
        val style = component.createBuilder().apply(builder).build()
        componentStyles[component] = (componentStyles[component] as? ComponentStyleDefinition<CS>)
            ?.addedTagStyle(this, style)
            ?: ComponentStyleDefinition.fromTag(this, style)
    }

    internal fun build(): StyleSheet = StyleSheet(
        colors = colorsBuilder.build(),
        contentStyle = commonBuilder.build(),
        componentStyles = componentStyles,
    )
}
