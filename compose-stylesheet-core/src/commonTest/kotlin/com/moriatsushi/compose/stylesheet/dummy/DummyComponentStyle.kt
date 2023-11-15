package com.moriatsushi.compose.stylesheet.dummy

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.token.Token

class DummyComponentStyle(
    val color1: Token<Color>? = null,
    val color2: Token<Color>? = null,
    override val commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
) : ComponentStyle
