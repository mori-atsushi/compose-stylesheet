package com.moriatsushi.compose.stylesheet.component.padding

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value

@Immutable
sealed class ComponentPadding {
    @Composable
    internal abstract fun asPaddingValues(): PaddingValues

    internal abstract fun copy(
        start: Token<Dp>? = null,
        top: Token<Dp>? = null,
        end: Token<Dp>? = null,
        bottom: Token<Dp>? = null,
        right: Token<Dp>? = null,
        left: Token<Dp>? = null,
    ): ComponentPadding
}

internal fun ComponentPadding(paddingValues: PaddingValues): ComponentPadding =
    ComponentPaddingImpl(paddingValues = paddingValues)

internal fun ComponentPadding(
    start: Token<Dp>? = null,
    top: Token<Dp>? = null,
    end: Token<Dp>? = null,
    bottom: Token<Dp>? = null,
    right: Token<Dp>? = null,
    left: Token<Dp>? = null,
): ComponentPadding = ComponentPaddingImpl(
    listOfNotNull(
        start?.let { PaddingSide.Start to it },
        top?.let { PaddingSide.Top to it },
        end?.let { PaddingSide.End to it },
        bottom?.let { PaddingSide.Bottom to it },
        right?.let { PaddingSide.Right to it },
        left?.let { PaddingSide.Left to it },
    ),
)

private data class ComponentPaddingImpl(
    private val values: List<Pair<PaddingSide, Token<Dp>>> = emptyList(),
    private val paddingValues: PaddingValues? = null,
) : ComponentPadding() {
    @Composable
    override fun asPaddingValues(): PaddingValues {
        val layoutDirection = LocalLayoutDirection.current

        val left = values.firstOrNull { it.first.isLeft(layoutDirection) }?.second?.value
            ?: paddingValues?.calculateLeftPadding(layoutDirection)
            ?: 0.dp

        val top = values.firstOrNull { it.first == PaddingSide.Top }?.second?.value
            ?: paddingValues?.calculateTopPadding()
            ?: 0.dp

        val right = values.firstOrNull { it.first.isRight(layoutDirection) }?.second?.value
            ?: paddingValues?.calculateRightPadding(layoutDirection)
            ?: 0.dp

        val bottom = values.firstOrNull { it.first == PaddingSide.Bottom }?.second?.value
            ?: paddingValues?.calculateBottomPadding()
            ?: 0.dp

        return PaddingValues.Absolute(left = left, top = top, right = right, bottom = bottom)
    }

    override fun copy(
        start: Token<Dp>?,
        top: Token<Dp>?,
        end: Token<Dp>?,
        bottom: Token<Dp>?,
        right: Token<Dp>?,
        left: Token<Dp>?,
    ): ComponentPadding {
        val newList = listOfNotNull(
            start?.let { PaddingSide.Start to it },
            top?.let { PaddingSide.Top to it },
            end?.let { PaddingSide.End to it },
            bottom?.let { PaddingSide.Bottom to it },
            right?.let { PaddingSide.Right to it },
            left?.let { PaddingSide.Left to it },
        )
        val newSides = newList.map { it.first }.toSet()
        return copy(values = newList + values.filterNot { newSides.contains(it.first) })
    }
}

private fun PaddingSide.isLeft(layoutDirection: LayoutDirection): Boolean {
    if (this == PaddingSide.Left) {
        return true
    }
    return if (layoutDirection == LayoutDirection.Ltr) {
        this == PaddingSide.Start
    } else {
        this == PaddingSide.End
    }
}

private fun PaddingSide.isRight(layoutDirection: LayoutDirection): Boolean {
    if (this == PaddingSide.Right) {
        return true
    }
    return if (layoutDirection == LayoutDirection.Ltr) {
        this == PaddingSide.End
    } else {
        this == PaddingSide.Start
    }
}

@Composable
@StyleSheetComponentApi
fun Modifier.componentPadding(padding: ComponentPadding?): Modifier = this.then(
    if (padding != null) Modifier.padding(padding.asPaddingValues()) else Modifier,
)

private enum class PaddingSide {
    Left, Top, Right, Bottom, Start, End,
}
