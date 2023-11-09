package com.moriatsushi.compose.stylesheet

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.color.ColorToken

@Immutable
internal data class ContentStyle(
    val color: ColorToken? = null,
) {
    companion object {
        val Empty = ContentStyle()
    }
}
