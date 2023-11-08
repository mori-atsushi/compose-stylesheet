package com.moriatsushi.compose.stylesheet.color

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * A token that represents a color.
 */
@Immutable
class ColorToken(
    private val name: String,
    internal val default: Color,
) {
    override fun toString(): String = "ColorToken($name, default=$default)"
}
