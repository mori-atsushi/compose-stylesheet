package com.moriatsushi.compose.stylesheet.dummy

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

class DummyComponentStyleBuilder : ComponentStyleBuilder<DummyComponentStyle>() {
    val color1: TokenSetter<Color> = TokenSetter()
    val color2: TokenSetter<Color> = TokenSetter()

    override fun plusAssign(other: DummyComponentStyle) {
        color1 += other.color1
        color2 += other.color2
        this += other.commonStyle
    }

    override fun build(): DummyComponentStyle = DummyComponentStyle(
        color1 = color1.token,
        color2 = color2.token,
        commonStyle = buildCommonStyle(),
    )
}
