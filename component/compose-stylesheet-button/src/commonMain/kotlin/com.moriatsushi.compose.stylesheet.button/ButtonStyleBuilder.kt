package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker

/**
 * A builder for [ButtonStyle].
 */
@StyleSheetBuilderMarker
class ButtonStyleBuilder internal constructor() :
    ButtonStateStyleBuilder(), StyleBuilder<ButtonStyle> {

    /**
     * A pressed style.
     */
    val pressed = ButtonStateStyleBuilder()

    /**
     * A hovered style.
     */
    val hovered = ButtonStateStyleBuilder()

    /**
     * A focused style.
     */
    val focused = ButtonStateStyleBuilder()

    /**
     * A disabled style.
     */
    val disabled = ButtonStateStyleBuilder()

    override fun plusAssign(other: ButtonStyle) {
        this += other.defaultStyle
        pressed += other.pressedStyle
        hovered += other.hoveredStyle
        focused += other.focusedStyle
        disabled += other.disabledStyle
    }

    override fun build(): ButtonStyle = ButtonStyle(
        defaultStyle = buildStateStyle(),
        pressedStyle = pressed.buildStateStyle(),
        hoveredStyle = hovered.buildStateStyle(),
        focusedStyle = focused.buildStateStyle(),
        disabledStyle = disabled.buildStateStyle(),
    )
}
