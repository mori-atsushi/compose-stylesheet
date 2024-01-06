package com.moriatsushi.compose.stylesheet.button

import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleBuilder
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyleHelper
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.indication.IndicationSetter

/**
 * A builder for [ButtonStateStyle].
 */
@StyleSheetBuilderMarker
open class ButtonStateStyleBuilder internal constructor(
    private val commonStyleHelper: ComponentCommonStyleHelper = ComponentCommonStyleHelper(),
) : ComponentCommonStyleBuilder by commonStyleHelper {
    /**
     * A layout of the button.
     */
    val layout: ButtonLayoutBuilder = ButtonLayoutBuilder()

    /**
     * An indication representing visual effects that occur when certain interactions happen, such
     * as pressing.
     *
     * Example:
     * ```
     * indication += SampleIndication()
     * indication += { rememberRipple() }
     * ```
     */
    val indication: IndicationSetter = IndicationSetter()

    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    operator fun invoke(builder: ButtonStateStyleBuilder.() -> Unit) {
        builder()
    }

    internal operator fun plusAssign(other: ButtonStateStyle) {
        layout += other.layout
        indication += other.indication
        this += other.commonStyle
        content += other.contentStyle
    }

    internal fun buildStateStyle(): ButtonStateStyle = ButtonStateStyle(
        layout = layout.build(),
        indication = indication.value,
        commonStyle = commonStyleHelper.buildCommonStyle(),
        contentStyle = content.build(),
    )
}
