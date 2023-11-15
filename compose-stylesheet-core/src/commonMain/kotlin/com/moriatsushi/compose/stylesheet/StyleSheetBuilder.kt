package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyleSet
import com.moriatsushi.compose.stylesheet.component.ComponentStyleSetBuilder
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
    private val componentStyleSet = mutableMapOf<Component<*, *>, ComponentStyleSetBuilder<*>>()

    /**
     * Merges the given [other] style sheet into this style sheet.
     */
    operator fun plusAssign(other: StyleSheet) {
        tokens += other.tokens
        commonBuilder += other.contentStyle
        for ((component, componentStyleSet) in other.componentStyles) {
            component += componentStyleSet
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
        val componentStyleSet = getOrPutComponentStyleSet(this)
        val commonStyleBuilder = componentStyleSet.commonStyleBuilder as SB
        commonStyleBuilder.builder()
    }

    /**
     * Defines a style for the given component and tag.
     */
    @Suppress("UNCHECKED_CAST")
    operator fun <CS : ComponentStyle, SB : StyleBuilder<CS>> Component<CS, SB>.invoke(
        tag: Tag<CS>,
        builder: SB.() -> Unit,
    ) {
        val componentStyleSet = getOrPutComponentStyleSet(this)
        val tagStyleBuilder = componentStyleSet.tagStyles.getOrPut(tag) { createBuilder() } as SB
        tagStyleBuilder.builder()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <CS : ComponentStyle, SB : StyleBuilder<CS>> getOrPutComponentStyleSet(
        component: Component<CS, SB>,
    ): ComponentStyleSetBuilder<CS> =
        componentStyleSet.getOrPut(component) {
            ComponentStyleSetBuilder.create(component.createBuilder())
        } as ComponentStyleSetBuilder<CS>

    @Suppress("UNCHECKED_CAST")
    private operator fun Component<*, *>.plusAssign(componentStyleSet: ComponentStyleSet<*>) {
        val component = this
        component as Component<ComponentStyle, StyleBuilder<ComponentStyle>>
        componentStyleSet as ComponentStyleSet<ComponentStyle>

        component { this += componentStyleSet.commonStyle }
        for ((tag, style) in componentStyleSet.tagStyles) {
            component(tag) { this += style }
        }
    }

    internal fun build(): StyleSheet = StyleSheet(
        tokens = tokens,
        contentStyle = commonBuilder.build(),
        componentStyles = componentStyleSet.mapValues { it.value.build() },
    )
}
