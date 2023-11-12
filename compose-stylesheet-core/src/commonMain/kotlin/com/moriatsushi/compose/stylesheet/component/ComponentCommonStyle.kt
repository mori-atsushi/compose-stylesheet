package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A common style for all components.
 */
@Stable
@StylesheetComponentApi
sealed interface ComponentCommonStyle {
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
    background: Token<Color>? = null,
    shape: Token<Shape?>? = null,
    border: Token<BorderStroke?>? = null,
): ComponentCommonStyle = ComponentCommonStyleImpl(
    background = background,
    shape = shape,
    border = border,
)

@Immutable
private data class ComponentCommonStyleImpl(
    override val background: Token<Color>?,
    override val shape: Token<Shape?>?,
    override val border: Token<BorderStroke?>?,
) : ComponentCommonStyle
