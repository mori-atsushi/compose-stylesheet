package com.moriatsushi.compose.stylesheet.indication

import androidx.compose.foundation.Indication
import androidx.compose.runtime.Composable
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import kotlin.jvm.JvmInline

@StyleSheetComponentApi
sealed interface IndicationStyle {
    val value: Indication?
        @Composable
        get
}

@StyleSheetComponentApi
internal fun IndicationStyle(builder: @Composable () -> Indication?): IndicationStyle =
    IndicationStyleImpl(builder)

@JvmInline
private value class IndicationStyleImpl(
    val builder: @Composable () -> Indication?,
) : IndicationStyle {
    override val value: Indication?
        @Composable
        get() = builder()
}
