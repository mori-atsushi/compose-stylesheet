package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.tag.Tag

internal class ComponentStyleDefinition<T : ComponentStyle>(
    val commonStyle: T,
    val tagStyles: Map<Tag<T>, T> = emptyMap(),
) {
    fun updatedCommonStyle(style: T): ComponentStyleDefinition<T> =
        ComponentStyleDefinition(
            commonStyle = style,
            tagStyles = tagStyles,
        )

    fun addedTagStyle(tag: Tag<T>, style: T): ComponentStyleDefinition<T> =
        ComponentStyleDefinition(
            commonStyle = commonStyle,
            tagStyles = tagStyles + (tag to style),
        )

    override fun toString(): String = "ComponentStyleDefinition(" +
        "commonStyle=$commonStyle," +
        "tagStyles=$tagStyles)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComponentStyleDefinition<*>) return false

        if (commonStyle != other.commonStyle) return false
        if (tagStyles != other.tagStyles) return false
        return true
    }

    override fun hashCode(): Int {
        var result = commonStyle.hashCode()
        result = 31 * result + tagStyles.hashCode()
        return result
    }

    companion object {
        fun <T : ComponentStyle> fromTag(
            tag: Tag<T>,
            style: T,
            defaultStyle: T,
        ): ComponentStyleDefinition<T> =
            ComponentStyleDefinition(
                commonStyle = defaultStyle,
                tagStyles = mapOf(tag to style),
            )
    }
}
