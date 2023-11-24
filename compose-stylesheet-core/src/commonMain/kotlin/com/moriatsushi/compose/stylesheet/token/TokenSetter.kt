package com.moriatsushi.compose.stylesheet.token

import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi

class TokenSetter<T> @StyleSheetComponentApi constructor() {
    @StyleSheetComponentApi
    var token: Token<T>? = null
        private set

    /**
     * Sets the given [token].
     */
    operator fun plusAssign(token: Token<T>?) {
        if (token != null) {
            this.token = token
        }
    }

    /**
     * Sets the given [value].
     */
    operator fun plusAssign(value: T) {
        this.token = Token(value)
    }
}
