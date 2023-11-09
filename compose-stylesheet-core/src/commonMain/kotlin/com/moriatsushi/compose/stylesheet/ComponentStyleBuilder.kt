package com.moriatsushi.compose.stylesheet

/**
 * An interface for marking a class as a component style builder.
 */
interface ComponentStyleBuilder<S : ComponentStyle> {
    fun build(): S
}
