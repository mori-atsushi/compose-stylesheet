package com.moriatsushi.compose.stylesheet.token

import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi

class TokenSetter<T> @StyleSheetComponentApi constructor() {
    var token: Token<T>? = null
        internal set
}
