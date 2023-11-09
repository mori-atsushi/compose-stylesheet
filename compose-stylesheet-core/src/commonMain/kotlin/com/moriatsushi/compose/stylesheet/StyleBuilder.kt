package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.color.ColorSetter
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.color.toToken

/**
 * An interface for building a [style][T].
 */
interface StyleBuilder<T> {
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

    fun build(): T
}
