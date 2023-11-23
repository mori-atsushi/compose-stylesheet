package com.moriatsushi.compose.stylesheet.component.size

import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.token.Token

class ComponentMinMaxSizeSetter internal constructor(
    private val onSet: (Token<Dp>) -> Unit,
) {
    operator fun plusAssign(token: Token<Dp>) {
        onSet(token)
    }

    operator fun plusAssign(size: Dp) {
        onSet(Token(size))
    }
}
