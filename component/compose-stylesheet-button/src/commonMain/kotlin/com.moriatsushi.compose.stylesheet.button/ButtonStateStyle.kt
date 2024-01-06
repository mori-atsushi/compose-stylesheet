package com.moriatsushi.compose.stylesheet.button

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.indication.IndicationStyle

/**
 * A style for each [Button] state, such as pressed, focused, etc.
 */
@Immutable
sealed interface ButtonStateStyle {
    val layout: ButtonLayout
    val indication: IndicationStyle?
    val contentStyle: ContentStyle
    val commonStyle: ComponentCommonStyle

    companion object {
        /**
         * Constant for a default [ButtonStateStyle].
         */
        val Default: ButtonStateStyle = ButtonStateStyle()
    }
}

internal fun ButtonStateStyle(builder: ButtonStateStyleBuilder.() -> Unit): ButtonStateStyle =
    ButtonStateStyleBuilder().apply(builder).buildStateStyle()

internal fun ButtonStateStyle(
    layout: ButtonLayout = ButtonLayout.Default,
    indication: IndicationStyle? = null,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
): ButtonStateStyle = ButtonStateStyleImpl(
    layout = layout,
    indication = indication,
    commonStyle = commonStyle,
    contentStyle = contentStyle,
)

@Immutable
private data class ButtonStateStyleImpl(
    override val layout: ButtonLayout,
    override val indication: IndicationStyle?,
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
) : ButtonStateStyle
