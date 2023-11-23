package com.moriatsushi.compose.stylesheet.component.size

import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.token.Token

class ComponentSizeValueSetter internal constructor(
    private val onSet: (ComponentSize.Value) -> Unit,
) {
    operator fun plusAssign(@Suppress("UNUSED_PARAMETER") fill: ComponentFillSize) {
        onSet(ComponentSize.Value.Fill)
    }

    operator fun plusAssign(token: Token<Dp>) {
        onSet(ComponentSize.Value.Fixed(token))
    }

    operator fun plusAssign(width: Dp) {
        this += Token(width)
    }
}
