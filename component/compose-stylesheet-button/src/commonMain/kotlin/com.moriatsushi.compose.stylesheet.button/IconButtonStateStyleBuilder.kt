package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [IconButtonStateStyle].
 */
class IconButtonStateStyleBuilder internal constructor() :
    ComponentStyleBuilder<IconButtonStateStyle>() {

    /**
     * A color of the icon.
     */
    private val color = TokenSetter<Color>()

    override fun plusAssign(other: IconButtonStateStyle) {
        color += other.color
        this += other.commonStyle
    }

    internal operator fun plusAssign(iconButtonStyle: IconButtonStyle) {
        color += iconButtonStyle.color
        this += iconButtonStyle.commonStyle
    }

    override fun build(): IconButtonStateStyle = IconButtonStateStyle(
        color = color.token,
        commonStyle = buildCommonStyle(),
    )
}
