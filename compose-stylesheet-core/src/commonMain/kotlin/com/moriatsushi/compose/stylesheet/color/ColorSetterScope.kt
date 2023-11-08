package com.moriatsushi.compose.stylesheet.color

import androidx.compose.ui.graphics.Color

/**
 * A scope for setting a color.
 */
interface ColorSetterScope {
    infix fun ColorSetter.to(token: ColorToken) {
        this.value = token
    }

    infix fun ColorSetter.to(color: Color) {
        this.value = color.toToken()
    }
}
