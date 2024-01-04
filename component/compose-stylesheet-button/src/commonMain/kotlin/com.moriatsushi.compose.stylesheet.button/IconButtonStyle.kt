package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.indication.IndicationStyle

/**
 * A style for [IconButton].
 */
@Immutable
sealed interface IconButtonStyle {
    val indication: IndicationStyle?
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
    indication: IndicationStyle? = null,
    defaultStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    pressedStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    hoveredStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    focusedStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
    disabledStyle: IconButtonStateStyle = IconButtonStateStyle.Default,
): IconButtonStyle = IconButtonStyleImpl(
    indication = indication,
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
    override val indication: IndicationStyle?,
    override val defaultStyle: IconButtonStateStyle,
    override val pressedStyle: IconButtonStateStyle,
    override val hoveredStyle: IconButtonStateStyle,
    override val focusedStyle: IconButtonStateStyle,
    override val disabledStyle: IconButtonStateStyle,
) : IconButtonStyle
