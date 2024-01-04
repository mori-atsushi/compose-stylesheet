package com.moriatsushi.compose.stylesheet.dummy

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.token.TokenSetter

class DummyComponentStyleBuilder(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : StyleBuilder<DummyComponentStyle>, ComponentCommonStyleBuilder by commonStyleHelper {
    val color1: TokenSetter<Color> = TokenSetter()
    val color2: TokenSetter<Color> = TokenSetter()
    val color3: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: DummyComponentStyle) {
        color1 += other.color1
        color2 += other.color2
        color3 += other.color3
        this += other.commonStyle
    }

    override fun build(): DummyComponentStyle = DummyComponentStyle(
        color1 = color1.token,
        color2 = color2.token,
        color3 = color3.token,
        commonStyle = commonStyleHelper.buildCommonStyle(),
    )
}
