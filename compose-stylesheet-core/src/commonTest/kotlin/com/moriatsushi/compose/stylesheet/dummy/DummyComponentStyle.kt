package com.moriatsushi.compose.stylesheet.dummy

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.token.Token

data class DummyComponentStyle(
    val color1: Token<Color>? = null,
    val color2: Token<Color>? = null,
    val color3: Token<Color>? = null,
    val commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
) {
    companion object {
        val Default = DummyComponentStyle()
    }
}

fun DummyComponentStyle(builder: DummyComponentStyleBuilder.() -> Unit): DummyComponentStyle =
    DummyComponentStyleBuilder().apply(builder).build()
