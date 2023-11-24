package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [IconButtonStyle].
 */
@StyleSheetBuilderMarker
class IconButtonStyleBuilder internal constructor() : ComponentStyleBuilder<IconButtonStyle>() {
    /**
     * A color of the icon.
     */
    val color = TokenSetter<Color>()

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
        color += other.color
        indication += other.indication
        this += other.commonStyle
        pressed += other.pressedStyle
        hovered += other.hoveredStyle
        focused += other.focusedStyle
        disabled += other.disabledStyle
    }

    override fun build(): IconButtonStyle = IconButtonStyle(
        color = color.token,
        indication = indication.value,
        commonStyle = buildCommonStyle(),
        pressedStyle = pressed.build(),
        hoveredStyle = hovered.build(),
        focusedStyle = focused.build(),
        disabledStyle = disabled.build(),
    )
}
