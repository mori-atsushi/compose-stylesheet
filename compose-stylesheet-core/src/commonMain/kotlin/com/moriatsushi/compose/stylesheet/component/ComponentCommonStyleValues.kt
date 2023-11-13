package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

/**
 * Concrete values of [ComponentCommonStyle].
 */
@Stable
@StyleSheetComponentApi
sealed interface ComponentCommonStyleValues {
    val background: Color
    val shape: Shape
    val border: BorderStroke?
}

internal fun ComponentCommonStyleValues(
    background: Color,
    shape: Shape,
    border: BorderStroke? = null,
): ComponentCommonStyleValues = ComponentCommonStyleValuesImpl(
    background = background,
    shape = shape,
    border = border,
)

private data class ComponentCommonStyleValuesImpl(
    override val background: Color,
    override val shape: Shape,
    override val border: BorderStroke?,
) : ComponentCommonStyleValues
