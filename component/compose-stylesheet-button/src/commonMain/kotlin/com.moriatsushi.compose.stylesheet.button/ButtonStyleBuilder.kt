package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter

/**
 * A builder for [ButtonStyle].
 */
@StyleSheetBuilderMarker
class ButtonStyleBuilder internal constructor() : ComponentStyleBuilder<ButtonStyle>() {
    private val pressedStyleBuilder = ButtonStateStyleBuilder()
    private val hoveredStyleBuilder = ButtonStateStyleBuilder()
    private val focusedStyleBuilder = ButtonStateStyleBuilder()
    private val disabledStyleBuilder = ButtonStateStyleBuilder()

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
     * Defines content styles.
     */
    fun content(builder: ContentStyleBuilder.() -> Unit) {
        content.builder()
    }

    /**
     * Defines pressed styles.
     */
    fun pressed(builder: ButtonStateStyleBuilder.() -> Unit) {
        pressedStyleBuilder.builder()
    }

    /**
     * Defines hovered styles.
     */
    fun hovered(builder: ButtonStateStyleBuilder.() -> Unit) {
        hoveredStyleBuilder.builder()
    }

    /**
     * Defines focused styles.
     */
    fun focused(builder: ButtonStateStyleBuilder.() -> Unit) {
        focusedStyleBuilder.builder()
    }

    /**
     * Defines disabled styles.
     */
    fun disabled(builder: ButtonStateStyleBuilder.() -> Unit) {
        disabledStyleBuilder.builder()
    }

    override fun plusAssign(other: ButtonStyle) {
        indication += other.indication
        this += other.commonStyle
        content += other.contentStyle
        pressedStyleBuilder += other.pressedStyle
        hoveredStyleBuilder += other.hoveredStyle
        focusedStyleBuilder += other.focusedStyle
        disabledStyleBuilder += other.disabledStyle
    }

    override fun build(): ButtonStyle = ButtonStyle(
        indication = indication.value,
        commonStyle = buildCommonStyle(),
        contentStyle = content.build(),
        pressedStyle = pressedStyleBuilder.build(),
        hoveredStyle = hoveredStyleBuilder.build(),
        focusedStyle = focusedStyleBuilder.build(),
        disabledStyle = disabledStyleBuilder.build(),
    )
}
