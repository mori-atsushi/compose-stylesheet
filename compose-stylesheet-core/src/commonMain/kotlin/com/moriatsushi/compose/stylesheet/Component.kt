package com.moriatsushi.compose.stylesheet

/**
 * An interface for marking a class as a component, which has a style and a style builder.
 */
interface Component<S : ComponentStyle, SB : ComponentStyleBuilder<S>> {
    val defaultStyle: S
    fun createBuilder(): SB
}
