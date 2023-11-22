package com.moriatsushi.compose.stylesheet.icon

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Icon].
 */
@Stable
sealed interface IconStyle : ComponentStyle {
    val color: Token<Color>?

    companion object {
        /**
         * Constant for a default [IconStyle].
         */
        val Default: IconStyle = IconStyle()
    }
}

/**
 * Creates a [IconStyle] using the [builder].
 */
fun IconStyle(builder: IconStyleBuilder.() -> Unit): IconStyle =
    IconStyleBuilder().apply(builder).build()

internal fun IconStyle(
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    color: Token<Color>? = null,
): IconStyle = IconStyleImpl(
    commonStyle = commonStyle,
    color = color,
)

@Immutable
private data class IconStyleImpl(
    override val commonStyle: ComponentCommonStyle,
    override val color: Token<Color>?,
) : IconStyle
