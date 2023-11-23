package com.moriatsushi.compose.stylesheet.component.size

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.moriatsushi.compose.stylesheet.token.Token

class ComponentSizeSetter internal constructor(
    private val onSet: (ComponentSize) -> Unit,
) {
    operator fun plusAssign(@Suppress("UNUSED_PARAMETER") fill: ComponentFillSize) {
        onSet(ComponentSize.Fill)
    }

    operator fun plusAssign(size: DpSize) {
        onSet(ComponentSize(size))
    }

    operator fun plusAssign(size: ComponentSize) {
        onSet(size)
    }

    operator fun plusAssign(size: Dp) {
        onSet(ComponentSize(size, size))
    }

    operator fun plusAssign(token: Token<Dp>) {
        onSet(ComponentSize(token, token))
    }
}
