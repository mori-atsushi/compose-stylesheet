package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.indication.IndicationStyle

/**
 * A style for [Button].
 */
@Immutable
sealed interface ButtonStyle : ComponentStyle {
    val indication: IndicationStyle?
    val contentStyle: ContentStyle
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
    indication: IndicationStyle? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
    pressedStyle: ButtonStateStyle = ButtonStateStyle.Default,
    hoveredStyle: ButtonStateStyle = ButtonStateStyle.Default,
    focusedStyle: ButtonStateStyle = ButtonStateStyle.Default,
    disabledStyle: ButtonStateStyle = ButtonStateStyle.Default,
): ButtonStyle = ButtonStyleImpl(
    indication = indication,
    commonStyle = commonStyle,
    contentStyle = contentStyle,
    pressedStyle = pressedStyle,
    hoveredStyle = hoveredStyle,
    focusedStyle = focusedStyle,
    disabledStyle = disabledStyle,
)

internal fun ButtonStyle.getStyleForState(
    isPressed: Boolean,
    isHovered: Boolean,
    isFocused: Boolean,
    isEnabled: Boolean,
): ButtonStateStyle {
    val buttonStyle = this
    return ButtonStateStyle {
        this += buttonStyle.commonStyle
        content += buttonStyle.contentStyle

        this += when {
            !isEnabled -> buttonStyle.disabledStyle
            isPressed -> buttonStyle.pressedStyle
            isHovered -> buttonStyle.hoveredStyle
            isFocused -> buttonStyle.focusedStyle
            else -> ButtonStateStyle.Default
        }
    }
}

@Immutable
private data class ButtonStyleImpl(
    override val indication: IndicationStyle?,
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
    override val pressedStyle: ButtonStateStyle,
    override val hoveredStyle: ButtonStateStyle,
    override val focusedStyle: ButtonStateStyle,
    override val disabledStyle: ButtonStateStyle,
) : ButtonStyle
