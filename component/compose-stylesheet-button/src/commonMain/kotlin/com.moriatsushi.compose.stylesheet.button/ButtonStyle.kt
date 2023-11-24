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
    val layout: ButtonLayout
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
    layout: ButtonLayout = ButtonLayout.Default,
    indication: IndicationStyle? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
    pressedStyle: ButtonStateStyle = ButtonStateStyle.Default,
    hoveredStyle: ButtonStateStyle = ButtonStateStyle.Default,
    focusedStyle: ButtonStateStyle = ButtonStateStyle.Default,
    disabledStyle: ButtonStateStyle = ButtonStateStyle.Default,
): ButtonStyle = ButtonStyleImpl(
    layout = layout,
    indication = indication,
    commonStyle = commonStyle,
    contentStyle = contentStyle,
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
        this += buttonStyle.commonStyle
        content += buttonStyle.contentStyle

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
    override val layout: ButtonLayout,
    override val indication: IndicationStyle?,
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
    override val pressedStyle: ButtonStateStyle,
    override val hoveredStyle: ButtonStateStyle,
    override val focusedStyle: ButtonStateStyle,
    override val disabledStyle: ButtonStateStyle,
) : ButtonStyle
