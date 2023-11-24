package com.moriatsushi.compose.stylesheet.border

import androidx.compose.foundation.BorderStroke
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token

class BorderSetter @StyleSheetComponentApi constructor() {
    @StyleSheetComponentApi
    var token: Token<BorderStyle?>? = null
        internal set

    operator fun plusAssign(token: Token<BorderStyle?>?) {
        if (token != null) {
            this.token = token
        }
    }

    operator fun plusAssign(border: BorderStyle) {
        this.token = Token(border)
    }

    operator fun plusAssign(border: BorderStroke) {
        this.token = Token(BorderStyle(border.width, border.brush))
    }
}
