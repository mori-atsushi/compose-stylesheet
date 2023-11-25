package com.moriatsushi.compose.stylesheet.divider

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [DividerStyle].
 */
@StyleSheetBuilderMarker
class DividerStyleBuilder internal constructor() : ComponentStyleBuilder<DividerStyle>() {
    /**
     * A thickness of the divider.
     */
    val thickness: TokenSetter<Dp> = TokenSetter()

    /**
     * A color of the divider.
     */
    val color: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: DividerStyle) {
        thickness += other.thickness
        color += other.color
        this += other.commonStyle
    }

    override fun build(): DividerStyle = DividerStyle(
        thickness = thickness.token,
        color = color.token,
        commonStyle = buildCommonStyle(),
    )
}
