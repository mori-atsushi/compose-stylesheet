package com.moriatsushi.compose.stylesheet.button

import androidx.compose.foundation.Indication
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [ButtonStyle].
 */
@StyleSheetBuilderMarker
class ButtonStyleBuilder internal constructor() : ComponentStyleBuilder<ButtonStyle>() {
    private val pressedStyleBuilder = ButtonStateStyleBuilder()

    /**
     * An indication representing visual effects that occur when certain interactions happen, such
     * as pressing.
     */
    val indication: TokenSetter<Indication?> = TokenSetter()

    /**
     * A content style.
     */
    val content: ContentStyleBuilder = ContentStyleBuilder()

    /**
     * Defines content styles.
     */
    fun content(builder: ContentStyleBuilder.() -> Unit) {
        content.builder()
    }

    /**
     * Defines pressed styles.
     */
    fun pressed(builder: ButtonStateStyleBuilder.() -> Unit) {
        pressedStyleBuilder.builder()
    }

    override fun plusAssign(other: ButtonStyle) {
        indication += other.indication
        this += other.commonStyle
        content += other.contentStyle
        pressedStyleBuilder += other.pressedStyle
    }

    override fun build(): ButtonStyle = ButtonStyle(
        indication = indication.token,
        commonStyle = buildCommonStyle(),
        contentStyle = content.build(),
        pressedStyle = pressedStyleBuilder.build(),
    )
}
