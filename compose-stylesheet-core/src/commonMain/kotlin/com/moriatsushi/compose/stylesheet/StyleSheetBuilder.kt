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
        tag: Tag<CS>? = null,
        builder: SB.() -> Unit,
    ) {
        val style = createBuilder().apply(builder).build()
        val currentDefinition = componentStyles[this] as? ComponentStyleDefinition<CS>
        componentStyles[this] = if (tag != null) {
            currentDefinition
                ?.addedTagStyle(tag, style)
                ?: ComponentStyleDefinition.fromTag(tag, style, defaultStyle)
        } else {
            currentDefinition
                ?.updatedCommonStyle(style)
                ?: ComponentStyleDefinition(style)
        }
    }

    internal fun build(): StyleSheet = StyleSheet(
        colors = colorsBuilder.build(),
        contentStyle = commonBuilder.build(),
        componentStyles = componentStyles,
    )
}
