package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.ComponentStyleSet
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
    internal val tokens: Map<ReferenceToken<*>, Token<*>> = emptyMap(),
    internal val contentStyle: ContentStyle = ContentStyle.Default,
    internal val componentStyles: Map<Component<*, *>, ComponentStyleSet<*>> = emptyMap(),
) {
    internal fun <T> getValue(token: Token<T>): T =
        when (token) {
            is ReferenceToken -> getValueRecursive(token)
            is SourceToken -> token.value
        }

    private tailrec fun <T> getValueRecursive(referenceToken: ReferenceToken<T>): T {
        @Suppress("UNCHECKED_CAST")
        val nextToken = tokens[referenceToken] as? Token<T>
            ?: referenceToken.default

        return when (nextToken) {
            is SourceToken -> nextToken.value
            is ReferenceToken -> getValueRecursive(nextToken)
        }
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <CS : Any, SB : StyleBuilder<CS>> getStyle(
        component: Component<CS, SB>,
        tags: TagModifier<CS> = TagModifier(),
    ): CS {
        val componentStyleSet = componentStyles[component] as? ComponentStyleSet<CS>
            ?: return component.defaultStyle

        if (tags.tags.isEmpty() || componentStyleSet.tagStyles.isEmpty()) {
            return componentStyleSet.commonStyle
        }

        return component.createBuilder().apply {
            this += componentStyleSet.commonStyle
            for (tag in tags.tags) {
                val tagStyle = componentStyleSet.tagStyles[tag]
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
         * Returns a new [StyleSheet] that is merged with the given [styleSheet]s. If there are
         * duplicated items, the latter one is used.
         */
        fun merge(vararg styleSheet: StyleSheet): StyleSheet = StyleSheet {
            for (item in styleSheet) {
                this += item
            }
        }

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
        fun <CS : Any, SB : StyleBuilder<CS>> getStyle(
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
