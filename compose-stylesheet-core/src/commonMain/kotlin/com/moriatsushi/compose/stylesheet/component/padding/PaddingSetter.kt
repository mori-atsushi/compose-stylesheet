package com.moriatsushi.compose.stylesheet.component.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.token.Token

class PaddingSetter internal constructor() {
    internal var value: ComponentPadding? = null

    operator fun plusAssign(padding: ComponentPadding?) {
        if (padding != null) {
            value = padding
        }
    }

    operator fun plusAssign(all: Token<Dp>) {
        value = ComponentPadding(all, all, all, all)
    }

    operator fun plusAssign(all: Dp) {
        this += Token(all)
    }

    operator fun plusAssign(values: PaddingValues) {
        value = ComponentPadding(values)
    }
}
