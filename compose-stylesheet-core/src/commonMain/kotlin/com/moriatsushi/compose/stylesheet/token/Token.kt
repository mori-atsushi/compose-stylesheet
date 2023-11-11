package com.moriatsushi.compose.stylesheet.token

/**
 * A token that represents a value.
 */
sealed class Token<out T>

internal class ReferenceToken<T>(
    private val name: String,
    val default: Token<T>,
) : Token<T>() {
    override fun toString(): String = "RefToken($name, default=$default)"
}

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
 * Returns a [Token] which has the given [name] and the given [default] token.
 */
fun <T> Token(name: String, default: Token<T>): Token<T> = ReferenceToken(name, default)

/**
 * Returns a [Token] which has the given [name] and the given [default] value.
 */
fun <T> Token(name: String, default: T): Token<T> = ReferenceToken(name, Token(default))

internal fun <T> Token(value: T): Token<T> = SourceToken(value)
