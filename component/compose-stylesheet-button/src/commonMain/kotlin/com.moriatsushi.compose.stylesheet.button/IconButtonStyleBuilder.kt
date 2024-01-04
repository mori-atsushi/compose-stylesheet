package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter

/**
 * A builder for [IconButtonStyle].
 */
@StyleSheetBuilderMarker
class IconButtonStyleBuilder internal constructor() :
    IconButtonStateStyleBuilder(), StyleBuilder<IconButtonStyle> {
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
    val pressed = IconButtonStateStyleBuilder()

    /**
     * A hovered style.
     */
    val hovered = IconButtonStateStyleBuilder()

    /**
     * A focused style.
     */
    val focused = IconButtonStateStyleBuilder()

    /**
     * A disabled style.
     */
    val disabled = IconButtonStateStyleBuilder()

    override fun plusAssign(other: IconButtonStyle) {
        indication += other.indication
        this += other.defaultStyle
        pressed += other.pressedStyle
        hovered += other.hoveredStyle
        focused += other.focusedStyle
        disabled += other.disabledStyle
    }

    override fun build(): IconButtonStyle = IconButtonStyle(
        indication = indication.value,
        defaultStyle = buildStateStyle(),
        pressedStyle = pressed.buildStateStyle(),
        hoveredStyle = hovered.buildStateStyle(),
        focusedStyle = focused.buildStateStyle(),
        disabledStyle = disabled.buildStateStyle(),
    )
}
