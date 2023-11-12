package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [ComponentStyle].
 */
abstract class ComponentStyleBuilder<T : ComponentStyle> : StyleBuilder<T> {
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

    @StylesheetComponentApi
    operator fun plusAssign(other: ComponentCommonStyle) {
        background += other.background
        shape += other.shape
        border += other.border
    }

    @StylesheetComponentApi
    fun buildCommonStyle(): ComponentCommonStyle = ComponentCommonStyle(
        background = background.token,
        shape = shape.token,
        border = border.token,
    )
}
