package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle

/**
 * A style for [Button].
 */
@Immutable
sealed interface ButtonStyle : ComponentStyle {
    val contentStyle: ContentStyle

    companion object {
        /**
         * Constant for a default [ButtonStyle].
         */
        val Default: ButtonStyle = ButtonStyle()
    }
}

/**
 * Creates a [ButtonStyle] using the [builder].
 */
fun ButtonStyle(builder: ButtonStyleBuilder.() -> Unit): ButtonStyle =
    ButtonStyleBuilder().apply(builder).build()

internal fun ButtonStyle(
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
): ButtonStyle = ButtonStyleImpl(
    commonStyle = commonStyle,
    contentStyle = contentStyle,
)

@Immutable
private data class ButtonStyleImpl(
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
) : ButtonStyle
