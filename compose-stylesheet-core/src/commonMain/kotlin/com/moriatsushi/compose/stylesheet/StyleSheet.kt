package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.ReferenceToken
import com.moriatsushi.compose.stylesheet.token.SourceToken
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style sheet that contains style definitions.
 */
@Immutable
class StyleSheet internal constructor(
    private val tokens: Map<Token<*>, Token<*>> = emptyMap(),
    internal val contentStyle: ContentStyle = ContentStyle.Default,
    private val componentStyles: Map<Component<*, *>, ComponentStyleDefinition<*>> = emptyMap(),
) {
    internal tailrec fun <T> getValue(token: Token<T>): T {
        @Suppress("UNCHECKED_CAST")
        var nextToken = tokens[token] as? Token<T>

        if (nextToken == null) {
            when (token) {
                is ReferenceToken -> {
                    nextToken = token.default
                }

                is SourceToken -> {
                    return token.value
                }
            }
        }

        return getValue(nextToken)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <CS : ComponentStyle, SB : StyleBuilder<CS>> getStyle(
        component: Component<CS, SB>,
        tags: TagModifier<CS> = TagModifier(),
    ): CS {
        val styleDefinition = componentStyles[component] as? ComponentStyleDefinition<CS>
            ?: return component.defaultStyle

        if (tags.tags.isEmpty() || styleDefinition.tagStyles.isEmpty()) {
            return styleDefinition.commonStyle
        }

        return component.createBuilder().apply {
            this += styleDefinition.commonStyle
            for (tag in tags.tags) {
                val tagStyle = styleDefinition.tagStyles[tag]
                if (tagStyle != null) {
                    this += tagStyle
                }
            }
        }.build()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StyleSheet) return false

        if (tokens != other.tokens) return false
        if (contentStyle != other.contentStyle) return false
        if (componentStyles != other.componentStyles) return false
        return true
    }

    override fun hashCode(): Int {
        var result = tokens.hashCode()
        result = 31 * result + contentStyle.hashCode()
        result = 31 * result + componentStyles.hashCode()
        return result
    }

    override fun toString(): String = "StyleSheet(" +
        "tokens=$tokens," +
        "contentStyle=$contentStyle," +
        "componentStyles=$componentStyles)"

    companion object {
        /**
         * Constant for an empty style sheet.
         */
        val Empty = StyleSheet()

        /**
         * Returns the value corresponding to the given [token].
         */
        @Composable
        fun <T> getValue(token: Token<T>): T =
            LocalStyleSheet.current.getValue(token)

        /**
         * Returns the [component] style, which is merged with the given [tags].
         */
        @Composable
        fun <CS : ComponentStyle, SB : StyleBuilder<CS>> getStyle(
            component: Component<CS, SB>,
            tags: TagModifier<CS> = TagModifier(),
        ): CS = LocalStyleSheet.current.getStyle(component, tags)

        /**
         * Creates a new [StyleSheet] using the given [builder].
         */
        operator fun invoke(builder: StyleSheetBuilder.() -> Unit): StyleSheet {
            val styleSheetBuilder = StyleSheetBuilder()
            builder(styleSheetBuilder)
            return styleSheetBuilder.build()
        }
    }
}
