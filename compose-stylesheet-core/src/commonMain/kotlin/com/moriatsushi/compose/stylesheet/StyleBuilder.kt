package com.moriatsushi.compose.stylesheet

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
     * Merges this style with the given [other] style.
     */
    operator fun plusAssign(other: T)

    fun build(): T
}
