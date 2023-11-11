package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.tag.Tag

/**
 * A style sheet that contains style definitions.
 */
@Immutable
class StyleSheet internal constructor(
    private val colors: Map<ColorToken, ColorToken> = emptyMap(),
    internal val contentStyle: ContentStyle = ContentStyle.Default,
    private val componentStyles: Map<Component<*, *>, ComponentStyleDefinition<*>> = emptyMap(),
) {
    internal tailrec fun getColor(token: ColorToken): Color {
        val nextToken = colors[token] ?: return token.default
        return getColor(nextToken)
    }

    @Suppress("UNCHECKED_CAST")
    internal fun <CS : ComponentStyle, SB : StyleBuilder<CS>> getStyle(
        component: Component<CS, SB>,
        tag: Tag<CS>? = null,
    ): CS {
        val styleDefinition = componentStyles[component] as? ComponentStyleDefinition<CS>
            ?: return component.defaultStyle

        val tagStyle = tag?.let { styleDefinition.getTagStyle(it) }
            ?: return styleDefinition.commonStyle

        return component.createBuilder().apply {
            this += styleDefinition.commonStyle
            this += tagStyle
        }.build()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StyleSheet) return false

        if (colors != other.colors) return false
        if (contentStyle != other.contentStyle) return false
        if (componentStyles != other.componentStyles) return false
        return true
    }

    override fun hashCode(): Int {
        var result = colors.hashCode()
        result = 31 * result + contentStyle.hashCode()
        result = 31 * result + componentStyles.hashCode()
        return result
    }

    override fun toString(): String = "StyleSheet(" +
        "colors=$colors," +
        "contentStyle=$contentStyle," +
        "componentStyles=$componentStyles)"

    companion object {
        /**
         * Constant for an empty style sheet.
         */
        val Empty = StyleSheet()

        /**
         * Returns the color corresponding to the given [token].
         */
        @Composable
        fun getColor(token: ColorToken): Color =
            LocalStyleSheet.current.getColor(token)

        /**
         * Returns the [component] style. If [tag] is specified, returns the style merged with the
         * [tag] style.
         */
        @Composable
        fun <CS : ComponentStyle, SB : StyleBuilder<CS>> getStyle(
            component: Component<CS, SB>,
            tag: Tag<CS>? = null,
        ): CS = LocalStyleSheet.current.getStyle(component, tag)

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
