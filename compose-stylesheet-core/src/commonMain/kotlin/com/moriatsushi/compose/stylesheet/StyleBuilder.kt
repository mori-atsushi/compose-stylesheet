package com.moriatsushi.compose.stylesheet

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * An interface for building a [style][T].
 */
interface StyleBuilder<T> {
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
     * Sets the given [color] to [this]. If the [color] is [Color.Unspecified], this does nothing.
     */
    operator fun TokenSetter<Color>.plusAssign(color: Color) {
        if (color.isSpecified) {
            this.token = Token(color)
        }
    }

    /**
     * Sets the given [size] to [this]. If the [size] is [TextUnit.Unspecified], this does
     * nothing.
     */
    operator fun TokenSetter<TextUnit>.plusAssign(size: TextUnit) {
        if (size.isSpecified) {
            this.token = Token(size)
        }
    }

    /**
     * Sets the given [size] to [this]. If the [size] is [Dp.Unspecified], this does nothing.
     */
    operator fun TokenSetter<Dp>.plusAssign(size: Dp) {
        if (size.isSpecified) {
            this.token = Token(size)
        }
    }

    /**
     * Merges this style with the given [other] style.
     */
    operator fun plusAssign(other: T)

    fun build(): T
}
