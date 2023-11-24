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
    private val pressedStyleBuilder = IconButtonStateStyleBuilder()
    private val hoveredStyleBuilder = IconButtonStateStyleBuilder()
    private val focusedStyleBuilder = IconButtonStateStyleBuilder()
    private val disabledStyleBuilder = IconButtonStateStyleBuilder()

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
     * Defines pressed styles.
     */
    fun pressed(builder: IconButtonStateStyleBuilder.() -> Unit) {
        pressedStyleBuilder.builder()
    }

    /**
     * Defines hovered styles.
     */
    fun hovered(builder: IconButtonStateStyleBuilder.() -> Unit) {
        hoveredStyleBuilder.builder()
    }

    /**
     * Defines focused styles.
     */
    fun focused(builder: IconButtonStateStyleBuilder.() -> Unit) {
        focusedStyleBuilder.builder()
    }

    /**
     * Defines disabled styles.
     */
    fun disabled(builder: IconButtonStateStyleBuilder.() -> Unit) {
        disabledStyleBuilder.builder()
    }

    override fun plusAssign(other: IconButtonStyle) {
        color += other.color
        indication += other.indication
        this += other.commonStyle
        pressedStyleBuilder += other.pressedStyle
        hoveredStyleBuilder += other.hoveredStyle
        focusedStyleBuilder += other.focusedStyle
        disabledStyleBuilder += other.disabledStyle
    }

    override fun build(): IconButtonStyle = IconButtonStyle(
        color = color.token,
        indication = indication.value,
        commonStyle = buildCommonStyle(),
        pressedStyle = pressedStyleBuilder.build(),
        hoveredStyle = hoveredStyleBuilder.build(),
        focusedStyle = focusedStyleBuilder.build(),
        disabledStyle = disabledStyleBuilder.build(),
    )
}
