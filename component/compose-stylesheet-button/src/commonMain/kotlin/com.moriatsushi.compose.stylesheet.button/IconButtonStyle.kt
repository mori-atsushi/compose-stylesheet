package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.indication.IndicationStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [IconButton].
 */
@Immutable
sealed interface IconButtonStyle {
    val color: Token<Color>?
    val indication: IndicationStyle?
    val commonStyle: ComponentCommonStyle
    val pressedStyle: IconButtonStateStyle
    val hoveredStyle: IconButtonStateStyle
    val focusedStyle: IconButtonStateStyle
    val disabledStyle: IconButtonStateStyle

    companion object {
        /**
         * Constant for a default [IconButtonStyle].
         */
        val Default: IconButtonStyle = IconButtonStyle()
    }
}

/**
 * Creates a [IconButtonStyle] using the [builder].
 */
fun IconButtonStyle(builder: IconButtonStyleBuilder.() -> Unit): IconButtonStyle =
    IconButtonStyleBuilder().apply(builder).build()

internal fun IconButtonStyle(
    color: Token<Color>? = null,
    indication: IndicationStyle? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    pressedStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    hoveredStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    focusedStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    disabledStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
): IconButtonStyle = IconButtonStyleImpl(
    color = color,
    indication = indication,
    commonStyle = commonStyle,
    pressedStyle = pressedStyle,
    hoveredStyle = hoveredStyle,
    focusedStyle = focusedStyle,
    disabledStyle = disabledStyle,
)

fun IconButtonStyle.getStyleForState(
    isEnabled: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    isFocused: Boolean,
): IconButtonStateStyle {
    val iconButtonStyle = this
    return IconButtonStateStyle {
        this += iconButtonStyle

        if (isFocused) {
            this += iconButtonStyle.focusedStyle
        }

        if (isHovered) {
            this += iconButtonStyle.hoveredStyle
        }

        if (isPressed) {
            this += iconButtonStyle.pressedStyle
        }

        if (!isEnabled) {
            this += iconButtonStyle.disabledStyle
        }
    }
}

@Immutable
private data class IconButtonStyleImpl(
    override val color: Token<Color>?,
    override val indication: IndicationStyle?,
    override val commonStyle: ComponentCommonStyle,
    override val pressedStyle: IconButtonStateStyle,
    override val hoveredStyle: IconButtonStateStyle,
    override val focusedStyle: IconButtonStateStyle,
    override val disabledStyle: IconButtonStateStyle,
) : IconButtonStyle
