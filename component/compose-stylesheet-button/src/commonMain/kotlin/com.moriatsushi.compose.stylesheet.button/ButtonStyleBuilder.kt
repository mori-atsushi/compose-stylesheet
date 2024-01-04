package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter

/**
 * A builder for [ButtonStyle].
 */
@StyleSheetBuilderMarker
class ButtonStyleBuilder internal constructor() :
    ButtonStateStyleBuilder(), StyleBuilder<ButtonStyle> {
    /**
     * A layout of the button.
     */
    val layout: ButtonLayoutBuilder = ButtonLayoutBuilder()

    /**
     * An indication representing visual effects that occur when certain interactions happen, such
     * as pressing.
     *
     * Example:
     * ```
     * indication += SampleIndication()
     * indication += { rememberRipple() }
     * ```
     */
    val indication: IndicationSetter = IndicationSetter()

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
        layout += other.layout
        indication += other.indication
        this += other.defaultStyle
        pressed += other.pressedStyle
        hovered += other.hoveredStyle
        focused += other.focusedStyle
        disabled += other.disabledStyle
    }

    override fun build(): ButtonStyle = ButtonStyle(
        layout = layout.build(),
        indication = indication.value,
        defaultStyle = buildStateStyle(),
        pressedStyle = pressed.buildStateStyle(),
        hoveredStyle = hovered.buildStateStyle(),
        focusedStyle = focused.buildStateStyle(),
        disabledStyle = disabled.buildStateStyle(),
    )
}
