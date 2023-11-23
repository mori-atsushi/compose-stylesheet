package com.moriatsushi.compose.stylesheet.component.size

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.isSpecified
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value
import kotlin.jvm.JvmInline

/**
 * A size of the component.
 */
@Immutable
internal data class ComponentSize(
    internal val width: Value? = null,
    internal val height: Value? = null,
    internal val minWidth: Token<Dp>? = null,
    internal val minHeight: Token<Dp>? = null,
    internal val maxWidth: Token<Dp>? = null,
    internal val maxHeight: Token<Dp>? = null,
) {
    constructor(size: DpSize) : this(
        width = Value.Fixed(Token(size.width)),
        height = Value.Fixed(Token(size.height)),
    )

    constructor(width: Token<Dp>, height: Token<Dp>) : this(
        width = Value.Fixed(width),
        height = Value.Fixed(height),
    )

    constructor(width: Dp, height: Dp) : this(
        width = Token(width),
        height = Token(height),
    )

    fun merge(other: ComponentSize): ComponentSize = ComponentSize(
        width = other.width ?: width,
        height = other.height ?: height,
        minWidth = other.minWidth ?: minWidth,
        minHeight = other.minHeight ?: minHeight,
        maxWidth = other.maxWidth ?: maxWidth,
        maxHeight = other.maxHeight ?: maxHeight,
    )

    @Immutable
    sealed interface Value {
        @Immutable
        @JvmInline
        value class Fixed(val token: Token<Dp>) : Value

        @Immutable
        data object Fill : Value
    }

    companion object {
        val Default: ComponentSize = ComponentSize()
        val Fill: ComponentSize = ComponentSize(Value.Fill, Value.Fill)
    }
}

@Composable
internal fun Modifier.size(size: ComponentSize): Modifier {
    val widthModifier = when (size.width) {
        is ComponentSize.Value.Fill -> Modifier.fillMaxWidth()

        is ComponentSize.Value.Fixed -> {
            val width = size.width.token.value
            if (width.isSpecified) Modifier.width(width) else Modifier
        }

        null -> Modifier
    }

    val widthInModifier = if (size.minWidth != null || size.maxWidth != null) {
        val minWidth = size.minWidth?.value ?: Dp.Unspecified
        val maxWidth = size.maxWidth?.value ?: Dp.Unspecified
        Modifier.widthIn(min = minWidth, max = maxWidth)
    } else {
        Modifier
    }

    val heightModifier = when (size.height) {
        is ComponentSize.Value.Fill -> Modifier.fillMaxHeight()

        is ComponentSize.Value.Fixed -> {
            val height = size.height.token.value
            if (height.isSpecified) Modifier.height(height) else Modifier
        }

        null -> Modifier
    }

    val heightInModifier = if (size.minHeight != null || size.maxHeight != null) {
        val minHeight = size.minHeight?.value ?: Dp.Unspecified
        val maxHeight = size.maxHeight?.value ?: Dp.Unspecified
        Modifier.heightIn(min = minHeight, max = maxHeight)
    } else {
        Modifier
    }

    return this
        .then(widthModifier)
        .then(widthInModifier)
        .then(heightModifier)
        .then(heightInModifier)
}
