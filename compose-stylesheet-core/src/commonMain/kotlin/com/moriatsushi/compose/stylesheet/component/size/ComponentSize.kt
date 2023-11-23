package com.moriatsushi.compose.stylesheet.component.size

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.isSpecified
import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi
import com.moriatsushi.compose.stylesheet.token.Token
import com.moriatsushi.compose.stylesheet.token.value
import kotlin.jvm.JvmInline

/**
 * A size of the component.
 */
@StyleSheetComponentApi
@Immutable
data class ComponentSize internal constructor(
    internal val width: Value? = null,
    internal val height: Value? = null,
) {
    internal constructor(size: DpSize) : this(
        width = Value.Fixed(Token(size.width)),
        height = Value.Fixed(Token(size.height)),
    )

    internal constructor(width: Token<Dp>, height: Token<Dp>) : this(
        width = Value.Fixed(width),
        height = Value.Fixed(height),
    )

    internal constructor(width: Dp, height: Dp) : this(
        width = Token(width),
        height = Token(height),
    )

    @Immutable
    internal sealed interface Value {
        @Immutable
        @JvmInline
        value class Fixed(val token: Token<Dp>) : Value

        @Immutable
        data object Fill : Value
    }

    companion object {
        internal val Default: ComponentSize = ComponentSize()
        internal val Fill: ComponentSize = ComponentSize(Value.Fill, Value.Fill)
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
    val heightModifier = when (size.height) {
        is ComponentSize.Value.Fill -> Modifier.fillMaxHeight()

        is ComponentSize.Value.Fixed -> {
            val height = size.height.token.value
            if (height.isSpecified) Modifier.height(height) else Modifier
        }

        null -> Modifier
    }
    return this.then(widthModifier).then(heightModifier)
}
