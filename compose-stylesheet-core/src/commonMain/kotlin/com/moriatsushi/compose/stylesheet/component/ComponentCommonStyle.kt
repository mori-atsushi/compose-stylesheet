package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import com.moriatsushi.compose.stylesheet.component.padding.ComponentPadding
import com.moriatsushi.compose.stylesheet.component.padding.componentPadding
import com.moriatsushi.compose.stylesheet.component.size.ComponentSize
import com.moriatsushi.compose.stylesheet.component.size.size
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

/**
 * A common style for all components.
 */
@Stable
@StyleSheetComponentApi
data class ComponentCommonStyle internal constructor(
    internal val size: ComponentSize = ComponentSize.Default,
    val padding: ComponentPadding? = null,
    internal val background: Token<Color>? = null,
    internal val shape: Token<Shape?>? = null,
    internal val border: Token<BorderStroke?>? = null,
) {
    companion object {
        /**
         * Constant for a default [ComponentCommonStyle].
         */
        val Default: ComponentCommonStyle = ComponentCommonStyle()
    }
}

/**
 * Applies the component common [style] to [this].
 *
 * @param includePadding If false, the padding will not be applied. Please call
 * [Modifier].[componentPadding] separately.
 */
@StyleSheetComponentApi
@Composable
fun Modifier.componentCommonStyle(
    style: ComponentCommonStyle,
    includePadding: Boolean = true,
): Modifier {
    val shape = style.shape?.value ?: RectangleShape

    val border = style.border?.value
    val borderModifier = if (border != null) {
        Modifier.border(border, shape)
    } else {
        Modifier
    }

    val background = style.background?.value ?: Color.Unspecified
    val backgroundModifier = if (background.isSpecified) {
        Modifier.background(background, shape)
    } else {
        Modifier
    }

    val clipModifier = if (shape != RectangleShape) Modifier.clip(shape) else Modifier

    val paddingModifier = if (includePadding) {
        Modifier.componentPadding(style.padding)
    } else {
        Modifier
    }

    return this
        .size(style.size)
        .then(borderModifier)
        .then(backgroundModifier)
        .then(clipModifier)
        .then(paddingModifier)
}
