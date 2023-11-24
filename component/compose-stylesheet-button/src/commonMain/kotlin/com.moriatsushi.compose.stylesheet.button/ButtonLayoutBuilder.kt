package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.StyleBuilder.ValueSetter
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.token.TokenSetter

@StyleSheetBuilderMarker
class ButtonLayoutBuilder internal constructor() {
    /**
     * A position of an icon.
     */
    val iconPosition = ValueSetter<ButtonIconPosition>()

    /**
     * Space between an icon and content.
     */
    val spaceBetweenIconAndContent = TokenSetter<Dp>()

    /**
     * Space between an icon and container.
     */
    val spaceBetweenIconAndContainer = TokenSetter<Dp>()

    /**
     * Space between content and container.
     */
    val spaceBetweenContentAndContainer = TokenSetter<Dp>()

    internal operator fun plusAssign(other: ButtonLayout) {
        iconPosition += other.iconPosition
        spaceBetweenIconAndContent += other.spaceBetweenIconAndContent
        spaceBetweenIconAndContainer += other.spaceBetweenIconAndContainer
        spaceBetweenContentAndContainer += other.spaceBetweenContentAndContainer
    }

    internal fun build(): ButtonLayout = ButtonLayout(
        iconPosition = iconPosition.value,
        spaceBetweenIconAndContent = spaceBetweenIconAndContent.token,
        spaceBetweenIconAndContainer = spaceBetweenIconAndContainer.token,
        spaceBetweenContentAndContainer = spaceBetweenContentAndContainer.token,
    )
}
