package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.token.Token

sealed interface ButtonLayout {
    val iconPosition: ButtonIconPosition?
    val spaceBetweenIconAndContent: Token<Dp>?
    val spaceBetweenIconAndContainer: Token<Dp>?
    val spaceBetweenContentAndContainer: Token<Dp>?

    companion object {
        /**
         * Constant for a default [ButtonLayout].
         */
        val Default: ButtonLayout = ButtonLayout()
    }
}

internal fun ButtonLayout(
    iconPosition: ButtonIconPosition? = null,
    spaceBetweenIconAndContent: Token<Dp>? = null,
    spaceBetweenIconAndContainer: Token<Dp>? = null,
    spaceBetweenContentAndContainer: Token<Dp>? = null,
): ButtonLayout = ButtonLayoutImpl(
    iconPosition = iconPosition,
    spaceBetweenIconAndContent = spaceBetweenIconAndContent,
    spaceBetweenIconAndContainer = spaceBetweenIconAndContainer,
    spaceBetweenContentAndContainer = spaceBetweenContentAndContainer,
)

private data class ButtonLayoutImpl(
    override val iconPosition: ButtonIconPosition?,
    override val spaceBetweenIconAndContent: Token<Dp>?,
    override val spaceBetweenIconAndContainer: Token<Dp>?,
    override val spaceBetweenContentAndContainer: Token<Dp>?,
) : ButtonLayout
