package com.moriatsushi.compose.stylesheet.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.border.BorderSetter
import com.moriatsushi.compose.stylesheet.component.padding.PaddingSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentMinMaxSizeSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentSize
import com.moriatsushi.compose.stylesheet.component.size.ComponentSizeSetter
import com.moriatsushi.compose.stylesheet.component.size.ComponentSizeValueSetter
import com.moriatsushi.compose.stylesheet.token.TokenSetter

@StyleSheetComponentApi
class ComponentCommonStyleHelper : ComponentCommonStyleBuilder {
    private var _size: ComponentSize = ComponentSize.Default

    override val size: ComponentSizeSetter = ComponentSizeSetter {
        _size = ComponentSize(
            width = it.width ?: _size.width,
            height = it.height ?: _size.height,
        )
    }

    override val minSize: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(minWidth = it, minHeight = it)
    }

    override val maxSize: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(maxWidth = it, maxHeight = it)
    }

    override val width: ComponentSizeValueSetter = ComponentSizeValueSetter {
        _size = _size.copy(width = it)
    }

    override val minWidth: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(minWidth = it)
    }

    override val maxWidth: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(maxWidth = it)
    }

    override val height: ComponentSizeValueSetter = ComponentSizeValueSetter {
        _size = _size.copy(height = it)
    }

    override val minHeight: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(minHeight = it)
    }

    override val maxHeight: ComponentMinMaxSizeSetter = ComponentMinMaxSizeSetter {
        _size = _size.copy(maxHeight = it)
    }

    override val padding: PaddingSetter = PaddingSetter()
    override val background: TokenSetter<Color> = TokenSetter()
    override val shape: TokenSetter<Shape?> = TokenSetter()
    override val border: BorderSetter = BorderSetter()
    override val outline: BorderSetter = BorderSetter()

    @StyleSheetComponentApi
    override fun plusAssign(other: ComponentCommonStyle) {
        _size = _size.merge(other.size)
        padding += other.padding
        background += other.background
        shape += other.shape
        border += other.border
        outline += other.outline
    }

    fun buildCommonStyle(): ComponentCommonStyle = ComponentCommonStyle(
        size = _size,
        padding = padding.value,
        background = background.token,
        shape = shape.token,
        border = border.value,
        outline = outline.value,
    )
}
