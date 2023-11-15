package com.moriatsushi.compose.stylesheet.component

import com.moriatsushi.compose.stylesheet.tag.Tag

internal data class ComponentStyleSet<T : ComponentStyle>(
    val commonStyle: T,
    val tagStyles: Map<Tag<T>, T> = emptyMap(),
)
