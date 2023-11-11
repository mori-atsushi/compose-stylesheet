package com.moriatsushi.compose.stylesheet.tag

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ComponentStyle
import kotlin.jvm.JvmInline

/**
 * A tag modifier for decorating a [Component].
 */
@Stable
sealed interface TagModifier<CS : ComponentStyle> {
    val tags: List<Tag<CS>>

    operator fun plus(other: TagModifier<CS>): TagModifier<CS> =
        ListTagModifier(tags + other.tags)
}

@Suppress("UNCHECKED_CAST")
fun <CS : ComponentStyle> TagModifier(): TagModifier<CS> =
    EmptyTagModifier as TagModifier<CS>

@Immutable
private data object EmptyTagModifier : TagModifier<Nothing> {
    override val tags: List<Tag<Nothing>> = emptyList()
}

@Immutable
@JvmInline
private value class ListTagModifier<CS : ComponentStyle>(
    override val tags: List<Tag<CS>>,
) : TagModifier<CS>
