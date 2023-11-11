package com.moriatsushi.compose.stylesheet.tag

import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ComponentStyle

/**
 * A symbol for decorating a [Component].
 */
class Tag<CS : ComponentStyle> internal constructor(
    private val name: String,
    private val component: Component<CS, *>,
) {
    override fun toString(): String = "Tag($name, for=$component)"
}

/**
 * Creates a symbol for decorating this [Component].
 */
fun <CS : ComponentStyle> Component<CS, *>.tag(name: String): Tag<CS> =
    Tag(name, this)
