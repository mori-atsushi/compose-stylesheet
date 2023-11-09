package com.moriatsushi.compose.stylesheet.tag

import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ComponentStyle
import com.moriatsushi.compose.stylesheet.StyleBuilder

/**
 * A symbol for decorating a [Component].
 */
interface Tag<CS : ComponentStyle, SB : StyleBuilder<CS>> {
    val component: Component<CS, SB>
}

/**
 * Creates a symbol for decorating this [Component].
 */
fun <CS : ComponentStyle, SB : StyleBuilder<CS>> Component<CS, SB>.tag(
    name: String,
): Tag<CS, SB> = TagImpl(name, this)

private class TagImpl<CS : ComponentStyle, SB : StyleBuilder<CS>>(
    private val name: String,
    override val component: Component<CS, SB>,
) : Tag<CS, SB> {
    override fun toString(): String = "Tag($name, for=$component)"
}
