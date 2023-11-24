package com.moriatsushi.compose.stylesheet.token

import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi

class TokenSetterCallback<T> @StyleSheetComponentApi constructor(
    private val onSet: (Token<T>) -> Unit,
) {
    /**
     * Sets the given [token].
     */
    operator fun plusAssign(token: Token<T>?) {
        if (token != null) {
            onSet(token)
        }
    }

    /**
     * Sets the given [value].
     */
    operator fun plusAssign(value: T) {
        onSet(Token(value))
    }
}
