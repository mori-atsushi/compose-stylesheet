package com.moriatsushi.compose.stylesheet.component

import com.moriatsushi.compose.stylesheet.StyleBuilder

/**
 * A symbol of a component, which has a style and a style builder.
 */
interface Component<CS : Any, SB : StyleBuilder<CS>> {
    val name: String
    val defaultStyle: CS
    fun createBuilder(): SB
}

/**
 * Creates a symbol of a component.
 */
@StyleSheetComponentApi
fun <CS : Any, SB : StyleBuilder<CS>> Component(
    name: String,
    defaultStyle: CS,
    createBuilder: () -> SB,
): Component<CS, SB> = ComponentImpl(name, defaultStyle, createBuilder)

private class ComponentImpl<CS : Any, SB : StyleBuilder<CS>>(
    override val name: String,
    override val defaultStyle: CS,
    private val builder: () -> SB,
) : Component<CS, SB> {
    override fun createBuilder(): SB = builder()

    override fun toString(): String = "Component($name)"
}
