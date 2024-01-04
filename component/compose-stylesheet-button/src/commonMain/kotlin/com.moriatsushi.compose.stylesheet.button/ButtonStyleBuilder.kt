package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter

/**
 * A builder for [ButtonStyle].
 */
@StyleSheetBuilderMarker
class ButtonStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : StyleBuilder<ButtonStyle>, ComponentCommonStyleBuilder by commonStyleHelper {
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
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

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
        this += other.commonStyle
        content += other.contentStyle
        pressed += other.pressedStyle
        hovered += other.hoveredStyle
        focused += other.focusedStyle
        disabled += other.disabledStyle
    }

    override fun build(): ButtonStyle = ButtonStyle(
        layout = layout.build(),
        indication = indication.value,
        commonStyle = commonStyleHelper.buildCommonStyle(),
        contentStyle = content.build(),
        pressedStyle = pressed.build(),
        hoveredStyle = hovered.build(),
        focusedStyle = focused.build(),
        disabledStyle = disabled.build(),
    )
}
