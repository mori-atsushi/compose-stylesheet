package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [IconButton].
 */
@Immutable
sealed interface IconButtonStyle : ComponentStyle {
    val color: Token<Color>?

    companion object {
        /**
         * Constant for a default [IconButtonStyle].
         */
        val Default: IconButtonStyle = IconButtonStyle()
    }
}

/**
 * Creates a [IconButtonStyle] using the [builder].
 */
fun IconButtonStyle(builder: IconButtonStyleBuilder.() -> Unit): IconButtonStyle =
    IconButtonStyleBuilder().apply(builder).build()

internal fun IconButtonStyle(
    color: Token<Color>? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
): IconButtonStyle = IconButtonStyleImpl(
    color = color,
    commonStyle = commonStyle,
)

@Immutable
private data class IconButtonStyleImpl(
    override val color: Token<Color>?,
    override val commonStyle: ComponentCommonStyle,
) : IconButtonStyle
