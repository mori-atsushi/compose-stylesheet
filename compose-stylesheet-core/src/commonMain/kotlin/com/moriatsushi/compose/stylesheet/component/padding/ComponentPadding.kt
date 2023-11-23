package com.moriatsushi.compose.stylesheet.component.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value
import kotlin.jvm.JvmInline

@Immutable
sealed interface ComponentPadding {
    @Composable
    @StyleSheetComponentApi
    fun asPaddingValues(): PaddingValues

    companion object {
        internal fun absolute(
            left: Token<Dp> = Token(0.dp),
            top: Token<Dp> = Token(0.dp),
            right: Token<Dp> = Token(0.dp),
            bottom: Token<Dp> = Token(0.dp),
        ): ComponentPadding = AbstractComponentPadding(
            left = left,
            top = top,
            right = right,
            bottom = bottom,
        )
    }
}

internal fun ComponentPadding(values: PaddingValues): ComponentPadding =
    PaddingValuesComponentPadding(values)

internal fun ComponentPadding(
    start: Token<Dp> = Token(0.dp),
    top: Token<Dp> = Token(0.dp),
    end: Token<Dp> = Token(0.dp),
    bottom: Token<Dp> = Token(0.dp),
): ComponentPadding = ComponentPaddingImpl(
    start = start,
    top = top,
    end = end,
    bottom = bottom,
)

@JvmInline
private value class PaddingValuesComponentPadding(
    private val paddingValues: PaddingValues,
) : ComponentPadding {
    @Composable
    override fun asPaddingValues(): PaddingValues = paddingValues
}

private data class AbstractComponentPadding(
    val left: Token<Dp>,
    val top: Token<Dp>,
    val right: Token<Dp>,
    val bottom: Token<Dp>,
) : ComponentPadding {
    @Composable
    override fun asPaddingValues(): PaddingValues = PaddingValues.Absolute(
        left = left.value,
        top = top.value,
        right = right.value,
        bottom = bottom.value,
    )
}

private data class ComponentPaddingImpl(
    val start: Token<Dp>,
    val top: Token<Dp>,
    val end: Token<Dp>,
    val bottom: Token<Dp>,
) : ComponentPadding {
    @Composable
    override fun asPaddingValues(): PaddingValues = PaddingValues(
        start = start.value,
        top = top.value,
        end = end.value,
        bottom = bottom.value,
    )
}

@Composable
internal fun Modifier.componentPadding(padding: ComponentPadding): Modifier =
    this.padding(padding.asPaddingValues())
