package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.color.ColorToken

@Immutable
internal data class CommonStyle(
    val color: ColorToken? = null,
) {
    companion object {
        val Empty = CommonStyle()
    }
}
