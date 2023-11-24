package com.moriatsushi.compose.stylesheet.button

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
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

    override fun plusAssign(other: IconButtonStyle) {
        color += other.color
        this += other.commonStyle
    }

    override fun build(): IconButtonStyle = IconButtonStyle(
        color = color.token,
        commonStyle = buildCommonStyle(),
    )
}
