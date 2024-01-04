package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for each [IconButton] state, such as pressed, focused, etc.
 */
@Immutable
sealed interface IconButtonStateStyle {
    val color: Token<Color>?
    val commonStyle: ComponentCommonStyle

    companion object {
        /**
         * Constant for a default [IconButtonStateStyle].
         */
        val Default: IconButtonStateStyle = IconButtonStateStyle()
    }
}

internal fun IconButtonStateStyle(
    builder: IconButtonStateStyleBuilder.() -> Unit,
): IconButtonStateStyle = IconButtonStateStyleBuilder().apply(builder).buildStateStyle()

internal fun IconButtonStateStyle(
    color: Token<Color>? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
): IconButtonStateStyle = IconButtonStateStyleImpl(
    color = color,
    commonStyle = commonStyle,
)

@Immutable
private data class IconButtonStateStyleImpl(
    override val color: Token<Color>?,
    override val commonStyle: ComponentCommonStyle,
) : IconButtonStateStyle
