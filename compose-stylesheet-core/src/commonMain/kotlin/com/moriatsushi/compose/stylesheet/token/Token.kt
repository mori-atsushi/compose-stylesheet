package com.moriatsushi.compose.stylesheet.token

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.StyleSheet

/**
 * A token that represents a value. There are two types of tokens:
 *
 * - A [ReferenceToken], which has a default value and can be overridden by the [StyleSheet].
 * - A source token, which has a concrete value and cannot be overridden.
 */
@Immutable
sealed class Token<out T>

@Immutable
class ReferenceToken<T> internal constructor(
    private val name: String,
    internal val default: Token<T>,
) : Token<T>() {
    override fun toString(): String = "ReferenceToken($name, default=$default)"
}

@Immutable
internal class SourceToken<T>(val value: T) : Token<T>() {
    override fun toString(): String = "SourceToken(<source>, value=$value)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SourceToken<*>) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int = value.hashCode()
}

/**
 * Returns the value corresponding to the given [Token] from the current [StyleSheet].
 */
val <T> Token<T>.value: T
    @Composable
    get() = StyleSheet.getValue(this)

/**
 * Returns a reference [Token] which has the given [name] and the given [default] token. The value
 * can be overridden by the [StyleSheet].
 */
@Suppress("FunctionName")
fun <T> Token(name: String, default: Token<T>): ReferenceToken<T> = ReferenceToken(name, default)

/**
 * Returns a reference [Token] which has the given [name] and the given [default] value. The value
 * can be overridden by the [StyleSheet].
 */
@Suppress("FunctionName")
fun <T> Token(name: String, default: T): ReferenceToken<T> = ReferenceToken(name, Token(default))

internal fun <T> Token(value: T): Token<T> = SourceToken(value)
