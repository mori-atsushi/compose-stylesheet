package com.moriatsushi.compose.stylesheet.component

import com.moriatsushi.compose.stylesheet.StyleBuilder

/**
 * A builder for a component style.
 */
abstract class ComponentStyleBuilder<T : Any> @StyleSheetComponentApi constructor(
    private val componentCommonStyleHelper: ComponentCommonStyleHelper =
        ComponentCommonStyleHelper(),
) : StyleBuilder<T>, ComponentCommonStyleBuilder by componentCommonStyleHelper {
    @StyleSheetComponentApi
    protected fun buildCommonStyle(): ComponentCommonStyle =
        componentCommonStyleHelper.buildCommonStyle()
}
