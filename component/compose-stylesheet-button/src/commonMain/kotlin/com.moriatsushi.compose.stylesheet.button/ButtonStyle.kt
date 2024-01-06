package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable

/**
 * A style for [Button].
 */
@Immutable
sealed interface ButtonStyle {
    val defaultStyle: ButtonStateStyle
    val pressedStyle: ButtonStateStyle
    val hoveredStyle: ButtonStateStyle
    val focusedStyle: ButtonStateStyle
    val disabledStyle: ButtonStateStyle

    companion object {
        /**
         * Constant for a default [ButtonStyle].
         */
        val Default: ButtonStyle = ButtonStyle()
    }
}

/**
 * Creates a [ButtonStyle] using the [builder].
 */
fun ButtonStyle(builder: ButtonStyleBuilder.() -> Unit): ButtonStyle =
    ButtonStyleBuilder().apply(builder).build()

internal fun ButtonStyle(
    defaultStyle: ButtonStateStyle = ButtonStateStyle.Default,
    pressedStyle: ButtonStateStyle = ButtonStateStyle.Default,
    hoveredStyle: ButtonStateStyle = ButtonStateStyle.Default,
    focusedStyle: ButtonStateStyle = ButtonStateStyle.Default,
    disabledStyle: ButtonStateStyle = ButtonStateStyle.Default,
): ButtonStyle = ButtonStyleImpl(
    defaultStyle = defaultStyle,
    pressedStyle = pressedStyle,
    hoveredStyle = hoveredStyle,
    focusedStyle = focusedStyle,
    disabledStyle = disabledStyle,
)

internal fun ButtonStyle.getStyleForState(
    isEnabled: Boolean,
    isPressed: Boolean,
    isHovered: Boolean,
    isFocused: Boolean,
): ButtonStateStyle {
    val buttonStyle = this
    return ButtonStateStyle {
        this += buttonStyle.defaultStyle

        if (isFocused) {
            this += buttonStyle.focusedStyle
        }

        if (isHovered) {
            this += buttonStyle.hoveredStyle
        }

        if (isPressed) {
            this += buttonStyle.pressedStyle
        }

        if (!isEnabled) {
            this += buttonStyle.disabledStyle
        }
    }
}

@Immutable
private data class ButtonStyleImpl(
    override val defaultStyle: ButtonStateStyle,
    override val pressedStyle: ButtonStateStyle,
    override val hoveredStyle: ButtonStateStyle,
    override val focusedStyle: ButtonStateStyle,
    override val disabledStyle: ButtonStateStyle,
) : ButtonStyle
