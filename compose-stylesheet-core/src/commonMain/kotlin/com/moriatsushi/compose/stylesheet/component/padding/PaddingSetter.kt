package com.moriatsushi.compose.stylesheet.component.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.TokenSetterCallback

class PaddingSetter internal constructor() {
    internal var value: ComponentPadding = ComponentPadding.Default

    val start: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(start = it)
    }
    val top: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(top = it)
    }
    val end: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(end = it)
    }
    val bottom: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(bottom = it)
    }
    val right: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(right = it)
    }
    val left: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(left = it)
    }
    val vertical: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(top = it, bottom = it)
    }
    val horizontal: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value.copy(start = it, end = it)
    }

    operator fun plusAssign(padding: ComponentPadding) {
        value = value.merge(padding)
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
