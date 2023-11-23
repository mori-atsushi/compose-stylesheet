package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.component.size.ComponentFillSize
import com.moriatsushi.compose.stylesheet.component.size.ComponentSize
import com.moriatsushi.compose.stylesheet.component.size.ComponentSizeSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentSizeValueSetter
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [ComponentStyle].
 */
abstract class ComponentStyleBuilder<T : ComponentStyle> : StyleBuilder<T> {
    private var _size: ComponentSize = ComponentSize.Default

    /**
     * A size of the component.
     *
     * Example:
     * ```
     * size += fill
     * size += DpSize(100.dp, 100.dp)
     * size += 100.dp // width and height are 100.dp
     * size += sizeToken // Token<Dp>
     * size += DpSize.Unspecified // Clear size
     * ```
     */
    val size: ComponentSizeSetter = ComponentSizeSetter {
        _size = ComponentSize(
            width = it.width ?: _size.width,
            height = it.height ?: _size.height,
        )
    }

    /**
     * A width of the component.
     *
     * Example:
     * ```
     * width += fill
     * width += 100.dp
     * width += sizeToken // Token<Dp>
     * width += Dp.Unspecified // Clear width
     * ```
     */
    val width: ComponentSizeValueSetter = ComponentSizeValueSetter {
        _size = _size.copy(width = it)
    }

    /**
     * A height of the component.
     *
     * Example:
     * ```
     * height += fill
     * height += 100.dp
     * height += sizeToken // Token<Dp>
     * height += Dp.Unspecified // Clear height
     * ```
     */
    val height: ComponentSizeValueSetter = ComponentSizeValueSetter {
        _size = _size.copy(height = it)
    }

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

    /**
     * A value for filling the component.
     */
    val fill: ComponentFillSize = ComponentFillSize.instance

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
}
