package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.unit.DpSize
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

/**
 * A common style for all components.
 */
@Stable
@StyleSheetComponentApi
sealed interface ComponentCommonStyle {
    val size: ComponentSize
    val background: Token<Color>?
    val shape: Token<Shape?>?
    val border: Token<BorderStroke?>?

    companion object {
        /**
         * Constant for a default [ComponentCommonStyle].
         */
        val Default: ComponentCommonStyle = ComponentCommonStyle()
    }
}

internal fun ComponentCommonStyle(
    size: ComponentSize = ComponentSize.Default,
    background: Token<Color>? = null,
    shape: Token<Shape?>? = null,
    border: Token<BorderStroke?>? = null,
): ComponentCommonStyle = ComponentCommonStyleImpl(
    size = size,
    background = background,
    shape = shape,
    border = border,
)

@Immutable
private data class ComponentCommonStyleImpl(
    override val size: ComponentSize,
    override val background: Token<Color>?,
    override val shape: Token<Shape?>?,
    override val border: Token<BorderStroke?>?,
) : ComponentCommonStyle

/**
 * Applies the component common [styleValues] to [this]. The [styleValues] need to be gotten from
 * [ComponentStyle.commonStyle].
 */
@StyleSheetComponentApi
@Composable
fun Modifier.componentCommonStyle(styleValues: ComponentCommonStyle): Modifier {
    val size = styleValues.size.asDpSize()
    val background = styleValues.background?.value ?: Color.Unspecified
    val shape = styleValues.shape?.value ?: RectangleShape
    val border = styleValues.border?.value

    return this
        .then(if (size != DpSize.Unspecified) Modifier.size(size) else Modifier)
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .then(if (background.isSpecified) Modifier.background(background, shape) else Modifier)
        .then(if (shape != RectangleShape) Modifier.clip(shape) else Modifier)
}
