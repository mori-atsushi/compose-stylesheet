package com.moriatsushi.compose.stylesheet.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpSize

/**
 * Concrete values of [ComponentCommonStyle].
 */
@Stable
@StyleSheetComponentApi
sealed interface ComponentCommonStyleValues {
    val size: DpSize
    val background: Color
    val shape: Shape
    val border: BorderStroke?
}

internal fun ComponentCommonStyleValues(
    size: DpSize,
    background: Color,
    shape: Shape,
    border: BorderStroke? = null,
): ComponentCommonStyleValues = ComponentCommonStyleValuesImpl(
    size = size,
    background = background,
    shape = shape,
    border = border,
)

private data class ComponentCommonStyleValuesImpl(
    override val size: DpSize,
    override val background: Color,
    override val shape: Shape,
    override val border: BorderStroke?,
) : ComponentCommonStyleValues
