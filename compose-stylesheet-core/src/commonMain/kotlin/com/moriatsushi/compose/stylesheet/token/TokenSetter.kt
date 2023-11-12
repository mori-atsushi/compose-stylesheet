package com.moriatsushi.compose.stylesheet.token

import com.moriatsushi.compose.stylesheet.component.StylesheetComponentApi

class TokenSetter<T> @StylesheetComponentApi constructor() {
    var token: Token<T>? = null
        internal set
}
