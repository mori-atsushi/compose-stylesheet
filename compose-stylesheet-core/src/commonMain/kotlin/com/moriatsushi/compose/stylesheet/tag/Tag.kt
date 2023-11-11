package com.moriatsushi.compose.stylesheet.tag

import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ComponentStyle

/**
 * A symbol for decorating a [Component].
 */
class Tag<CS : ComponentStyle> internal constructor(
    private val name: String,
    private val componentName: String,
) {
    override fun toString(): String = "Tag($name, component=$componentName)"
}

fun <CS : ComponentStyle> Tag(name: String, component: Component<CS, *>): Tag<CS> =
    Tag(name, component.name)
