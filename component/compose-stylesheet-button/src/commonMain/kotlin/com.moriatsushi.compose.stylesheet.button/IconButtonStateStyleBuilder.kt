package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [IconButtonStateStyle].
 */
@StyleSheetBuilderMarker
class IconButtonStateStyleBuilder internal constructor() :
    ComponentStyleBuilder<IconButtonStateStyle>() {

    /**
     * A color of the icon.
     */
    val color = TokenSetter<Color>()

    override fun plusAssign(other: IconButtonStateStyle) {
        color += other.color
        this += other.commonStyle
    }

    internal operator fun plusAssign(iconButtonStyle: IconButtonStyle) {
        color += iconButtonStyle.color
        this += iconButtonStyle.commonStyle
    }

    operator fun invoke(builder: IconButtonStateStyleBuilder.() -> Unit) {
        builder()
    }

    override fun build(): IconButtonStateStyle = IconButtonStateStyle(
        color = color.token,
        commonStyle = buildCommonStyle(),
    )
}
