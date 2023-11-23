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
 * Applies the component common [styleValues] to [this]. The [styleValues] need to be gotten from
 * [ComponentStyle.commonStyle].
 */
@StyleSheetComponentApi
@Composable
fun Modifier.componentCommonStyle(styleValues: ComponentCommonStyle): Modifier {
    val background = styleValues.background?.value ?: Color.Unspecified
    val shape = styleValues.shape?.value ?: RectangleShape
    val border = styleValues.border?.value

    return this
        .size(styleValues.size)
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .then(if (background.isSpecified) Modifier.background(background, shape) else Modifier)
        .then(if (shape != RectangleShape) Modifier.clip(shape) else Modifier)
}
