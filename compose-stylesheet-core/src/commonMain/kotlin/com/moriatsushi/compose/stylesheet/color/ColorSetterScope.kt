package com.moriatsushi.compose.stylesheet.color

import androidx.compose.ui.graphics.Color

/**
 * A scope for setting a color.
 */
interface ColorSetterScope {
    /**
     * Sets the color to the given [token].
     */
    operator fun ColorSetter.plusAssign(token: ColorToken) {
        this.value = token
    }

    /**
     * Sets the color to the given [color].
     */
    operator fun ColorSetter.plusAssign(color: Color) {
        this.value = color.toToken()
    }
}
