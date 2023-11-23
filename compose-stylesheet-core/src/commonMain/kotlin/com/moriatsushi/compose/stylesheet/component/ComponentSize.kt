package com.moriatsushi.compose.stylesheet.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

/**
 * A size of the component.
 */
@StyleSheetComponentApi
@Immutable
data class ComponentSize(
    val width: Token<Dp>? = null,
    val height: Token<Dp>? = null,
) {
    companion object {
        /**
         * Constant for a default [ComponentSize].
         */
        val Default: ComponentSize = ComponentSize()
    }
}

@Composable
internal fun ComponentSize.asDpSize(): DpSize = DpSize(
    width = width?.value ?: Dp.Unspecified,
    height = height?.value ?: Dp.Unspecified,
)
