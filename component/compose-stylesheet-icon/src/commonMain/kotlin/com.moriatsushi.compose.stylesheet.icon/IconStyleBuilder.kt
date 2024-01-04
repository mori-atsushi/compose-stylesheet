package com.moriatsushi.compose.stylesheet.icon

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [IconStyle].
 */
@StyleSheetBuilderMarker
class IconStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : StyleBuilder<IconStyle>, ComponentCommonStyleBuilder by commonStyleHelper {
    /**
     * A tint color.
     */
    var color: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: IconStyle) {
        this += other.commonStyle
        color += other.color
    }

    override fun build(): IconStyle = IconStyle(
        commonStyle = commonStyleHelper.buildCommonStyle(),
        color = color.token,
    )
}
