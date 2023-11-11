package com.moriatsushi.compose.stylesheet

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
        tokens = tokens,
        contentStyle = commonBuilder.build(),
        componentStyles = componentStyles,
    )
}
