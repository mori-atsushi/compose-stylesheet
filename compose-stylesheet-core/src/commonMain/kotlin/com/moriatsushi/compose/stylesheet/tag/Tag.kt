package com.moriatsushi.compose.stylesheet.tag

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.Component

/**
 * A symbol for decorating a [Component].
 */
@Immutable
class Tag<CS : Any> internal constructor(
    private val name: String,
    private val componentName: String,
) : TagModifier<CS> {
    override val tags: List<Tag<CS>> = listOf(this)

    override fun toString(): String = "Tag($name, component=$componentName)"
}

fun <CS : Any> Tag(name: String, component: Component<CS, *>): Tag<CS> =
    Tag(name, component.name)
