package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyleDefinition
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.tag.Tag
import com.moriatsushi.compose.stylesheet.token.SourceToken
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A builder for [StyleSheet].
 */
@StyleSheetBuilderMarker
class StyleSheetBuilder internal constructor() {
    private val tokens = mutableMapOf<Token<*>, Token<*>>()
    private val commonBuilder = ContentStyleBuilder()
    private val componentStyles = mutableMapOf<Component<*, *>, ComponentStyleDefinition<*>>()

    @Suppress("UNCHECKED_CAST")
    operator fun plusAssign(other: StyleSheet) {
        tokens += other.tokens
        commonBuilder += other.contentStyle
        for ((component, definition) in other.componentStyles) {
            component as Component<ComponentStyle, StyleBuilder<ComponentStyle>>
            definition as ComponentStyleDefinition<ComponentStyle>

            component { this += definition.commonStyle }
            for ((tag, style) in definition.tagStyles) {
                component(tag) { this += style }
            }
        }
    }

    /**
     * Overrides the [this] token with the given [token].
     */
    operator fun <T> Token<T>.plusAssign(token: Token<T>) {
        check(this !is SourceToken) { "$this is a source token and cannot be overridden" }
        tokens[this] = token
    }

    /**
     * Overrides the [this] token with the given [value].
     */
    operator fun <T> Token<T>.plusAssign(value: T) {
        check(this !is SourceToken) { "$this is a source token and cannot be overridden" }
        tokens[this] = Token(value)
    }

    /**
     * Defines content styles.
     */
    fun content(builder: ContentStyleBuilder.() -> Unit) {
        commonBuilder.builder()
    }

    /**
     * Defines a common style for the given component.
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <CS : ComponentStyle, SB : StyleBuilder<CS>> Component<CS, SB>.invoke(
        builder: SB.() -> Unit,
    ) {
        val currentDefinition = componentStyles[this] as? ComponentStyleDefinition<CS>
        val style = createBuilder().apply {
            if (currentDefinition != null) {
                this += currentDefinition.commonStyle
            }
            builder()
        }.build()

        componentStyles[this] = currentDefinition
            ?.updatedCommonStyle(style)
            ?: ComponentStyleDefinition(style)
    }

    /**
     * Defines a style for the given component and tag.
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <CS : ComponentStyle, SB : StyleBuilder<CS>> Component<CS, SB>.invoke(
        tag: Tag<CS>,
        builder: SB.() -> Unit,
    ) {
        val currentDefinition = componentStyles[this] as? ComponentStyleDefinition<CS>
        val currentTagStyle = currentDefinition?.tagStyles?.get(tag)
        val style = createBuilder().apply {
            if (currentTagStyle != null) {
                this += currentTagStyle
            }
            builder()
        }.build()

        componentStyles[this] = currentDefinition
            ?.addedTagStyle(tag, style)
            ?: ComponentStyleDefinition.fromTag(tag, style, defaultStyle)
    }

    internal fun build(): StyleSheet = StyleSheet(
        tokens = tokens,
        contentStyle = commonBuilder.build(),
        componentStyles = componentStyles,
    )
}
