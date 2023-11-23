package com.moriatsushi.compose.stylesheet.button

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Button].
 */
@Immutable
sealed interface ButtonStyle : ComponentStyle {
    val indication: Token<Indication?>?
    val contentStyle: ContentStyle
    val pressedStyle: ButtonStateStyle

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
    indication: Token<Indication?>? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
    pressedStyle: ButtonStateStyle = ButtonStateStyle.Default,
): ButtonStyle = ButtonStyleImpl(
    indication = indication,
    commonStyle = commonStyle,
    contentStyle = contentStyle,
    pressedStyle = pressedStyle,
)

internal fun ButtonStyle.getStyleForState(
    isPressed: Boolean,
): ButtonStateStyle {
    val buttonStyle = this
    return ButtonStateStyle {
        this += buttonStyle.commonStyle
        content += buttonStyle.contentStyle

        if (isPressed) {
            this += buttonStyle.pressedStyle
        }
    }
}

@Immutable
private data class ButtonStyleImpl(
    override val indication: Token<Indication?>?,
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
    override val pressedStyle: ButtonStateStyle,
) : ButtonStyle
