package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [ComponentStyle].
 */
abstract class ComponentStyleBuilder<T : ComponentStyle> : StyleBuilder<T> {
    private var _size: ComponentSize = ComponentSize.Default

    /**
     * A size of the component.
     */
    val size: SizeSetter = SizeSetter()

    /**
     * A width of the component.
     */
    val width: WidthSetter = WidthSetter()

    /**
     * A height of the component.
     */
    val height: HeightSetter = HeightSetter()

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
        size = _size,
        background = background.token,
        shape = shape.token,
        border = border.token,
    )

    inner class SizeSetter {
        operator fun plusAssign(size: DpSize) {
            _size = ComponentSize(width = Token(size.width), height = Token(size.height))
        }

        operator fun plusAssign(size: ComponentSize) {
            _size = size
        }
    }

    inner class WidthSetter {
        operator fun plusAssign(token: Token<Dp>) {
            _size = _size.copy(width = token)
        }

        operator fun plusAssign(width: Dp) {
            this += Token(width)
        }
    }

    inner class HeightSetter {
        operator fun plusAssign(token: Token<Dp>) {
            _size = _size.copy(height = token)
        }

        operator fun plusAssign(height: Dp) {
            this += Token(height)
        }
    }
}
