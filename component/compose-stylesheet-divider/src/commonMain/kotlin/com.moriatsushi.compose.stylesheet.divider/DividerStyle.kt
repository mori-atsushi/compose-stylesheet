package com.moriatsushi.compose.stylesheet.divider

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A style for [Divider].
 */
@Immutable
sealed interface DividerStyle : ComponentStyle {
    val thickness: Token<Dp>?
    val color: Token<Color>?
    val orientation: DividerOrientation?

    companion object {
        /**
         * Constant for a default [DividerStyle].
         */
        val Default: DividerStyle = DividerStyle()
    }
}

/**
 * Creates a [DividerStyle] using the [builder].
 */
fun DividerStyle(builder: DividerStyleBuilder.() -> Unit): DividerStyle =
    DividerStyleBuilder().apply(builder).build()

internal fun DividerStyle(
    thickness: Token<Dp>? = null,
    color: Token<Color>? = null,
    orientation: DividerOrientation? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
): DividerStyle = DividerStyleImpl(
    thickness = thickness,
    color = color,
    orientation = orientation,
    commonStyle = commonStyle,
)

@Immutable
private data class DividerStyleImpl(
    override val thickness: Token<Dp>?,
    override val color: Token<Color>?,
    override val orientation: DividerOrientation?,
    override val commonStyle: ComponentCommonStyle,
) : DividerStyle
