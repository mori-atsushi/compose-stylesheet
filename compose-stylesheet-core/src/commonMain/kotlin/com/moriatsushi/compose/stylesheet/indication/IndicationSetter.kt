package com.moriatsushi.compose.stylesheet.indication

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi

class IndicationSetter @StyleSheetComponentApi constructor() {
    @StyleSheetComponentApi
    var value: IndicationStyle? = null
        private set

    @StyleSheetComponentApi
    operator fun plusAssign(style: IndicationStyle?) {
        if (style != null) {
            this.value = style
        }
    }

    operator fun plusAssign(indication: Indication?) {
        this.value = IndicationStyle { indication }
    }

    operator fun plusAssign(builder: @Composable () -> Indication?) {
        this.value = IndicationStyle(builder)
    }
}
