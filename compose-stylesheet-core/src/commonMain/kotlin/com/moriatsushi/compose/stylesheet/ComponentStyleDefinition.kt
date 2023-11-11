package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.tag.Tag

internal class ComponentStyleDefinition<T : ComponentStyle>(
    val commonStyle: T,
    val tagStyles: Map<Tag<T, *>, T> = emptyMap(),
) {
    fun updatedCommonStyle(style: T): ComponentStyleDefinition<T> =
        ComponentStyleDefinition(
            commonStyle = style,
            tagStyles = tagStyles,
        )

    fun addedTagStyle(tag: Tag<T, *>, style: T): ComponentStyleDefinition<T> =
        ComponentStyleDefinition(
            commonStyle = commonStyle,
            tagStyles = tagStyles + (tag to style),
        )

    companion object {
        fun <T : ComponentStyle> fromTag(tag: Tag<T, *>, style: T): ComponentStyleDefinition<T> =
            ComponentStyleDefinition(
                commonStyle = tag.component.defaultStyle,
                tagStyles = mapOf(tag to style),
            )
    }
}
