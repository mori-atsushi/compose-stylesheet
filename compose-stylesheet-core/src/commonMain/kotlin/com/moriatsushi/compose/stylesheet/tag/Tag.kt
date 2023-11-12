package com.moriatsushi.compose.stylesheet.tag

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.ComponentStyle

/**
 * A symbol for decorating a [Component].
 */
@Immutable
class Tag<CS : ComponentStyle> internal constructor(
    private val name: String,
    private val componentName: String,
) : TagModifier<CS> {
    override val tags: List<Tag<CS>> = listOf(this)

    override fun toString(): String = "Tag($name, component=$componentName)"
}

fun <CS : ComponentStyle> Tag(name: String, component: Component<CS, *>): Tag<CS> =
    Tag(name, component.name)
