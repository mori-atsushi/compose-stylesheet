package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A style for [IconButton].
 */
@StyleSheetBuilderMarker
class IconButtonStyleBuilder internal constructor() : ComponentStyleBuilder<IconButtonStyle>() {
    /**
     * A color of the icon.
     */
    private val color = TokenSetter<Color>()

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

    override fun plusAssign(other: IconButtonStyle) {
        color += other.color
        indication += other.indication
        this += other.commonStyle
    }

    override fun build(): IconButtonStyle = IconButtonStyle(
        color = color.token,
        indication = indication.value,
        commonStyle = buildCommonStyle(),
    )
}
