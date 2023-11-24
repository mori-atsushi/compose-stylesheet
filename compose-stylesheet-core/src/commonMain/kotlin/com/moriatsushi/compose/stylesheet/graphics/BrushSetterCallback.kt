package com.moriatsushi.compose.stylesheet.graphics

import androidx.compose.ui.graphics.Brush
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token

class BrushSetterCallback @StyleSheetComponentApi constructor(
    private val onSet: (Token<BrushStyle>) -> Unit,
) {
    /**
     * Sets the given [token].
     */
    operator fun plusAssign(token: Token<BrushStyle>?) {
        if (token != null) {
            onSet(token)
        }
    }

    /**
     * Sets the given [brush].
     */
    operator fun plusAssign(brush: BrushStyle) {
        onSet(Token(brush))
    }

    /**
     * Sets the given [brush].
     */
    operator fun plusAssign(brush: Brush) {
        onSet(Token(BrushStyle(brush)))
    }
}
