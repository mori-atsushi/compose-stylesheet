package com.moriatsushi.compose.stylesheet.icon

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [IconStyle].
 */
@StyleSheetBuilderMarker
class IconStyleBuilder internal constructor() : ComponentStyleBuilder<IconStyle>() {
    /**
     * A tint color.
     */
    var color: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: IconStyle) {
        this += other.commonStyle
        color += other.color
    }

    override fun build(): IconStyle = IconStyle(
        commonStyle = buildCommonStyle(),
        color = color.token,
    )
}
