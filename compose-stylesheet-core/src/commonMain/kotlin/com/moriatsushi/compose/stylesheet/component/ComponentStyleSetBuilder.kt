package com.moriatsushi.compose.stylesheet.component

import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.tag.Tag

internal class ComponentStyleSetBuilder<T : Any>(
    val commonStyleBuilder: StyleBuilder<T>,
    val tagStyles: MutableMap<Tag<T>, StyleBuilder<T>>,
) {
    fun build(): ComponentStyleSet<T> =
        ComponentStyleSet(
            commonStyle = commonStyleBuilder.build(),
            tagStyles = tagStyles.mapValues { it.value.build() },
        )

    companion object {
        fun <T : Any> create(
            commonStyleBuilder: StyleBuilder<T>,
        ): ComponentStyleSetBuilder<T> =
            ComponentStyleSetBuilder(
                commonStyleBuilder = commonStyleBuilder,
                tagStyles = mutableMapOf(),
            )
    }
}
