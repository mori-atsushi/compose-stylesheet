package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.component.ComponentStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle

/**
 * A style for each [Button] state, such as pressed, focused, etc.
 */
@Immutable
sealed interface ButtonStateStyle : ComponentStyle {
    val contentStyle: ContentStyle

    companion object {
        /**
         * Constant for a default [ButtonStateStyle].
         */
        val Default: ButtonStateStyle = ButtonStateStyle()
    }
}

internal fun ButtonStateStyle(builder: ButtonStateStyleBuilder.() -> Unit): ButtonStateStyle =
    ButtonStateStyleBuilder().apply(builder).build()

internal fun ButtonStateStyle(
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
): ButtonStateStyle = ButtonStateStyleImpl(
    commonStyle = commonStyle,
    contentStyle = contentStyle,
)

@Immutable
private data class ButtonStateStyleImpl(
    override val commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    override val contentStyle: ContentStyle = ContentStyle.Default,
) : ButtonStateStyle
