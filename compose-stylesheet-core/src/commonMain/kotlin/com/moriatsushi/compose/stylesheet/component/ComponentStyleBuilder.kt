package com.moriatsushi.compose.stylesheet.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.border.BorderSetter
import com.moriatsushi.compose.stylesheet.component.padding.ComponentPadding
import com.moriatsushi.compose.stylesheet.component.padding.PaddingSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentFillSize
import com.moriatsushi.compose.stylesheet.component.size.ComponentMinMaxSizeSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentSize
import com.moriatsushi.compose.stylesheet.component.size.ComponentSizeSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentSizeValueSetter
import com.moriatsushi.compose.stylesheet.token.Token
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
     * A minimum size of the component.
     *
     * Example:
     * ```
     * minSize += 100.dp
     * minSize += sizeToken // Token<Dp>
     * ```
     */
    val minSize: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(minWidth = it, minHeight = it)
    }

    /**
     * A maximum size of the component.
     *
     * Example:
     * ```
     * maxSize += 100.dp
     * maxSize += sizeToken // Token<Dp>
     * ```
     */
    val maxSize: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(maxWidth = it, maxHeight = it)
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
     * A minimum width of the component.
     *
     * Example:
     * ```
     * minWidth += 100.dp
     * minWidth += sizeToken // Token<Dp>
     * ```
     */
    val minWidth: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(minWidth = it)
    }

    /**
     * A maximum width of the component.
     *
     * Example:
     * ```
     * maxWidth += 100.dp
     * maxWidth += sizeToken // Token<Dp>
     * ```
     */
    val maxWidth: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(maxWidth = it)
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
     * A minimum height of the component.
     *
     * Example:
     * ```
     * minHeight += 100.dp
     * minHeight += sizeToken // Token<Dp>
     * ```
     */
    val minHeight: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(minHeight = it)
    }

    /**
     * A maximum height of the component.
     *
     * Example:
     * ```
     * maxHeight += 100.dp
     * maxHeight += sizeToken // Token<Dp>
     * ```
     */
    val maxHeight: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(maxHeight = it)
    }

    /**
     * A padding of the component.
     *
     * Example:
     * ```
     * padding += 10.dp
     * padding += sizeToken // Token<Dp>
     * padding += padding(horizontal = 10.dp, vertical = 20.dp)
     * padding += padding(horizontal = horizontalToken, vertical = verticalToken)
     * padding += padding(start = 10.dp, top = 20.dp, end = 30.dp, bottom = 40.dp)
     * padding += absolutePadding(left = 10.dp, top = 20.dp, right = 30.dp, bottom = 40.dp)
     * padding += PaddingValues(10.dp)
     * ```
     */
    val padding: PaddingSetter = PaddingSetter()

    /**
     * A background color.
     */
    val background: TokenSetter<Color> = TokenSetter()

    /**
     * A shape for clipping the component.
     */
    val shape: TokenSetter<Shape?> = TokenSetter()

    /**
     * An inner border for the component.
     *
     * Example:
     * ```
     * border += BorderStroke(1.dp, Color.Black)
     * border += BorderStyle.Unspecified // Clear border
     * border.width += 1.dp
     * border.color += Color.Black
     * border.brush += Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
     * ```
     */
    val border: BorderSetter = BorderSetter()

    /**
     * An outer border for the component. This is not included in the size of the component.
     *
     * Example:
     * ```
     * outline += BorderStroke(1.dp, Color.Black)
     * outline += BorderStyle.Unspecified // Clear outline
     * outline.width += 1.dp
     * outline.color += Color.Black
     * outline.brush += Brush.horizontalGradient(listOf(Color.Red, Color.Blue))
     * ```
     */
    val outline: BorderSetter = BorderSetter()

    /**
     * A value for filling the component.
     */
    val fill: ComponentFillSize = ComponentFillSize.instance

    fun padding(
        horizontal: Token<Dp> = Token(0.dp),
        vertical: Token<Dp> = Token(0.dp),
    ): ComponentPadding =
        ComponentPadding(start = horizontal, top = vertical, end = horizontal, bottom = vertical)

    fun padding(
        horizontal: Dp = 0.dp,
        vertical: Dp = 0.dp,
    ): ComponentPadding = padding(Token(horizontal), Token(vertical))

    fun padding(
        start: Token<Dp> = Token(0.dp),
        top: Token<Dp> = Token(0.dp),
        end: Token<Dp> = Token(0.dp),
        bottom: Token<Dp> = Token(0.dp),
    ): ComponentPadding = ComponentPadding(
        start = start,
        top = top,
        end = end,
        bottom = bottom,
    )

    fun padding(
        start: Dp = 0.dp,
        top: Dp = 0.dp,
        end: Dp = 0.dp,
        bottom: Dp = 0.dp,
    ): ComponentPadding = ComponentPadding(
        start = Token(start),
        top = Token(top),
        end = Token(end),
        bottom = Token(bottom),
    )

    fun absolutePadding(
        left: Token<Dp> = Token(0.dp),
        top: Token<Dp> = Token(0.dp),
        right: Token<Dp> = Token(0.dp),
        bottom: Token<Dp> = Token(0.dp),
    ): ComponentPadding = ComponentPadding.absolute(
        left = left,
        top = top,
        right = right,
        bottom = bottom,
    )

    fun absolutePadding(
        left: Dp = 0.dp,
        top: Dp = 0.dp,
        right: Dp = 0.dp,
        bottom: Dp = 0.dp,
    ): ComponentPadding = ComponentPadding.absolute(
        left = Token(left),
        top = Token(top),
        right = Token(right),
        bottom = Token(bottom),
    )

    @StyleSheetComponentApi
    operator fun plusAssign(other: ComponentCommonStyle) {
        _size = _size.merge(other.size)
        padding += other.padding
        background += other.background
        shape += other.shape
        border += other.border
        outline += other.outline
    }

    @StyleSheetComponentApi
    protected fun buildCommonStyle(): ComponentCommonStyle = ComponentCommonStyle(
        size = _size,
        padding = padding.value,
        background = background.token,
        shape = shape.token,
        border = border.value,
        outline = outline.value,
    )
}
