package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [ComponentStyle].
 */
abstract class ComponentStyleBuilder<T : ComponentStyle> : StyleBuilder<T> {
    /**
     * A size of the component.
     */
    val size: TokenSetter<DpSize> = TokenSetter()

    /**
     * A background color.
     */
    val background: TokenSetter<Color> = TokenSetter()

    /**
     * A shape for clipping the component.
     */
    val shape: TokenSetter<Shape?> = TokenSetter()

    /**
     * A background border.
     */
    val border: TokenSetter<BorderStroke?> = TokenSetter()

    @StyleSheetComponentApi
    operator fun plusAssign(other: ComponentCommonStyle) {
        size += other.size
        background += other.background
        shape += other.shape
        border += other.border
    }

    @StyleSheetComponentApi
    fun buildCommonStyle(): ComponentCommonStyle = ComponentCommonStyle(
        size = size.token,
        background = background.token,
        shape = shape.token,
        border = border.token,
    )
}
