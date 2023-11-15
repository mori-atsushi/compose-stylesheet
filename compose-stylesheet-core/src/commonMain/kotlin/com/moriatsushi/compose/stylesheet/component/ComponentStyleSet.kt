package com.moriatsushi.compose.stylesheet.component

import com.moriatsushi.compose.stylesheet.tag.Tag

internal data class ComponentStyleSet<T : ComponentStyle>(
    val commonStyle: T,
    val tagStyles: Map<Tag<T>, T> = emptyMap(),
) {
    fun updatedCommonStyle(style: T): ComponentStyleSet<T> =
        ComponentStyleSet(
            commonStyle = style,
            tagStyles = tagStyles,
        )

    fun addedTagStyle(tag: Tag<T>, style: T): ComponentStyleSet<T> =
        ComponentStyleSet(
            commonStyle = commonStyle,
            tagStyles = tagStyles + (tag to style),
        )

    companion object {
        fun <T : ComponentStyle> fromTag(
            tag: Tag<T>,
            style: T,
            defaultStyle: T,
        ): ComponentStyleSet<T> =
            ComponentStyleSet(
                commonStyle = defaultStyle,
                tagStyles = mapOf(tag to style),
            )
    }
}
