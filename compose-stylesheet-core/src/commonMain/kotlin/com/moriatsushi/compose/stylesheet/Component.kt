package com.moriatsushi.compose.stylesheet

/**
 * A symbol of a component, which has a style and a style builder.
 */
interface Component<S : ComponentStyle, SB : ComponentStyleBuilder<S>> {
    val defaultStyle: S
    fun createBuilder(): SB
}

/**
 * Creates a symbol of a component.
 */
fun <S : ComponentStyle, SB : ComponentStyleBuilder<S>> Component(
    name: String,
    defaultStyle: S,
    createBuilder: () -> SB,
): Component<S, SB> = ComponentImpl(name, defaultStyle, createBuilder)

private class ComponentImpl<S : ComponentStyle, SB : ComponentStyleBuilder<S>>(
    private val name: String,
    override val defaultStyle: S,
    private val builder: () -> SB,
) : Component<S, SB> {
    override fun createBuilder(): SB = builder()

    override fun toString(): String = "Component($name)"
}
