package com.moriatsushi.compose.stylesheet

import com.moriatsushi.compose.stylesheet.component.StyleSheetComponentApi

/**
 * An interface for building a [style][T].
 */
interface StyleBuilder<T> {
    /**
     * Merges this style with the given [other] style.
     */
    operator fun plusAssign(other: T)

    fun build(): T

    class ValueSetter<T> @StyleSheetComponentApi constructor() {
        @StyleSheetComponentApi
        var value: T? = null
            private set

        /**
         * Sets the given [value].
         */
        operator fun plusAssign(value: T?) {
            if (value != null) {
                this.value = value
            }
        }
    }
}
