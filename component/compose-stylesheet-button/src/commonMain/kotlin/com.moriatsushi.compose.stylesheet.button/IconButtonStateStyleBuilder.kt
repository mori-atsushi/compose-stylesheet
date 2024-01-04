package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [IconButtonStateStyle].
 */
@StyleSheetBuilderMarker
class IconButtonStateStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : ComponentCommonStyleBuilder by commonStyleHelper {

    /**
     * A color of the icon.
     */
    val color = TokenSetter<Color>()

    operator fun plusAssign(other: IconButtonStateStyle) {
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

    fun build(): IconButtonStateStyle = IconButtonStateStyle(
        color = color.token,
        commonStyle = commonStyleHelper.buildCommonStyle(),
    )
}
