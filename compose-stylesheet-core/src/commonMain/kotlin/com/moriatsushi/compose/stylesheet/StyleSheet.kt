package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorToken

/**
 * A style sheet that contains style definitions.
 */
@Immutable
class StyleSheet internal constructor(
    private val colors: Map<ColorToken, ColorToken> = emptyMap(),
    private val common: CommonStyle = CommonStyle.Empty,
) {
    internal val color: Color = common.color
        ?.let(::getColor)
        ?: Color.Unspecified

    internal tailrec fun getColor(token: ColorToken): Color {
        val nextToken = colors[token] ?: return token.default
        return getColor(nextToken)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StyleSheet) return false

        if (colors != other.colors) return false
        if (common != other.common) return false
        return true
    }

    override fun hashCode(): Int {
        var result = colors.hashCode()
        result = 31 * result + common.hashCode()
        return result
    }

    override fun toString(): String = "StyleSheet(" +
        "colors=$colors," +
        "common=$common)"

    companion object {
        /**
         * Constant for an empty style sheet.
         */
        val Empty = StyleSheet()

        /**
         * A content color, which is used for text and icons.
         */
        val color: Color
            @Composable
            get() = LocalStyleSheet.current.color

        /**
         * Returns the color corresponding to the given [token].
         */
        @Composable
        fun getColor(token: ColorToken): Color =
            LocalStyleSheet.current.getColor(token)

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
