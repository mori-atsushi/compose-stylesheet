package com.moriatsushi.compose.stylesheet.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.DpSize
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An interface for marking a class as a component style.
 */
interface ComponentStyle {
    val commonStyle: ComponentCommonStyle
}

/**
 * Gets concrete values of [ComponentCommonStyle] to use Modifier.[componentCommonStyle].
 */
@StyleSheetComponentApi
val ComponentStyle.commonStyleValues: ComponentCommonStyleValues
    @Composable
    get() = ComponentCommonStyleValues(
        size = commonStyle.size?.value ?: DpSize.Unspecified,
        background = commonStyle.background?.value ?: Color.Unspecified,
        shape = commonStyle.shape?.value ?: RectangleShape,
        border = commonStyle.border?.value,
    )
