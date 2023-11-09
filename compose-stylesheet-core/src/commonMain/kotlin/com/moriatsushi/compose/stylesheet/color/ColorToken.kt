package com.moriatsushi.compose.stylesheet.color

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import com.moriatsushi.compose.stylesheet.StyleSheet

/**
 * A token that represents a color.
 */
@Immutable
class ColorToken(
    private val name: String,
    internal val default: Color,
) {
    override fun toString(): String = "ColorToken($name, default=$default)"

    companion object {
        /**
         * Constant for an unspecified [ColorToken].
         */
        val Unspecified = ColorToken("<unspecified>", Color.Unspecified)
    }
}

internal fun Color.toToken(): ColorToken =
    if (isSpecified) ColorToken("<source>", this) else ColorToken.Unspecified

/**
 * `false` when this is [Color.Unspecified].
 */
@Stable
val ColorToken.isSpecified: Boolean get() = default != Color.Unspecified

/**
 * `true` when this is [Color.Unspecified].
 */
@Stable
val ColorToken.isUnspecified: Boolean get() = default == Color.Unspecified

/**
 * Returns the color corresponding to the given [ColorToken].
 */
@Composable
fun ColorToken.asColor(): Color = StyleSheet.getColor(this)
