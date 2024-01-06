package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable

/**
 * A style for [IconButton].
 */
@Immutable
sealed interface IconButtonStyle {
    val defaultStyle: IconButtonStateStyle
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
    defaultStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    pressedStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    hoveredStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    focusedStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    disabledStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
): IconButtonStyle = IconButtonStyleImpl(
    defaultStyle = defaultStyle,
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
): IconButtonStateStyle = IconButtonStateStyle {
    this += defaultStyle

    if (isFocused) {
        this += focusedStyle
    }

    if (isHovered) {
        this += hoveredStyle
    }

    if (isPressed) {
        this += pressedStyle
    }

    if (!isEnabled) {
        this += disabledStyle
    }
}

@Immutable
private data class IconButtonStyleImpl(
    override val defaultStyle: IconButtonStateStyle,
    override val pressedStyle: IconButtonStateStyle,
    override val hoveredStyle: IconButtonStateStyle,
    override val focusedStyle: IconButtonStateStyle,
    override val disabledStyle: IconButtonStateStyle,
) : IconButtonStyle
