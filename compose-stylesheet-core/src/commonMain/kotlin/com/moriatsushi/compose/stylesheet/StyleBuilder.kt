package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import com.moriatsushi.compose.stylesheet.color.ColorSetter
import com.moriatsushi.compose.stylesheet.color.ColorToken
import com.moriatsushi.compose.stylesheet.color.isSpecified
import com.moriatsushi.compose.stylesheet.color.toToken
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * An interface for building a [style][T].
 */
interface StyleBuilder<T> {
    /**
     * Sets the color to the given [token]. If the [token] is [ColorToken.Unspecified], the color is
     * not set.
     */
    operator fun ColorSetter.plusAssign(token: ColorToken) {
        if (token.isSpecified) {
            this.value = token
        }
    }

    /**
     * Sets the color to the given [color]. If the [color] is [Color.Unspecified], the color is not
     * set.
     */
    operator fun ColorSetter.plusAssign(color: Color) {
        if (color.isSpecified) {
            this.value = color.toToken()
        }
    }

    /**
     * Sets the given [token] to [this].
     */
    operator fun <T> TokenSetter<T>.plusAssign(token: Token<T>?) {
        if (token != null) {
            this.token = token
        }
    }

    /**
     * Sets the given [value] to [this].
     */
    operator fun <T> TokenSetter<T>.plusAssign(value: T) {
        this.token = Token(value)
    }

    /**
     * Merges this style with the given [other] style.
     */
    operator fun plusAssign(other: T)

    fun build(): T
}
