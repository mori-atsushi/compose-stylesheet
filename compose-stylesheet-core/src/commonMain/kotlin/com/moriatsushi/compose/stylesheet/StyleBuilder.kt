package com.moriatsushi.compose.stylesheet

/**
 * An interface for building a [style][T].
 */
interface StyleBuilder<T> {
    /**
     * Merges this style with the given [other] style.
     */
    operator fun plusAssign(other: T)

    fun build(): T
}
