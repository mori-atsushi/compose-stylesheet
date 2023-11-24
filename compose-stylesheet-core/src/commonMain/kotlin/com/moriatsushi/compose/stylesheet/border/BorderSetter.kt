package com.moriatsushi.compose.stylesheet.border

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.graphics.BrushSetterCallback
import com.moriatsushi.compose.stylesheet.graphics.BrushStyle
import com.moriatsushi.compose.stylesheet.token.TokenSetterCallback

class BorderSetter @StyleSheetComponentApi constructor() {
    @StyleSheetComponentApi
    var value: BorderStyle? = null
        private set

    /**
     * A width of the border.
     */
    val width: TokenSetterCallback<Dp> = TokenSetterCallback {
        value = value?.copy(width = it) ?: BorderStyle(width = it)
    }

    /**
     * A brush of the border.
     */
    val brush: BrushSetterCallback = BrushSetterCallback {
        value = value?.copy(brush = it) ?: BorderStyle(brush = it)
    }

    /**
     * A color of the border.
     */
    val color: TokenSetterCallback<Color> = TokenSetterCallback {
        brush += BrushStyle(it)
    }

    operator fun plusAssign(border: BorderStyle?) {
        if (border != null) {
            value = border
        }
    }

    operator fun plusAssign(border: BorderStroke) {
        this.value = BorderStyle(border)
    }
}
