package com.moriatsushi.compose.stylesheet.appbar

import androidx.compose.runtime.Immutable
import com.moriatsushi.compose.stylesheet.component.ComponentCommonStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle

/**
 * A style for [TopAppBar].
 */
@Immutable
sealed interface TopAppBarStyle {
    val layout: TopAppBarLayout
    val contentStyle: ContentStyle
    val commonStyle: ComponentCommonStyle

    companion object {
        /**
         * Constant for a default [TopAppBarStyle].
         */
        val Default: TopAppBarStyle = TopAppBarStyle()
    }
}

/**
 * Creates a [TopAppBarStyle] using the [builder].
 */
fun TopAppBarStyle(builder: TopAppBarStyleBuilder.() -> Unit): TopAppBarStyle =
    TopAppBarStyleBuilder().apply(builder).build()

internal fun TopAppBarStyle(
    layout: TopAppBarLayout = TopAppBarLayout.Default,
    commonStyle: ComponentCommonStyle = ComponentCommonStyle.Default,
    contentStyle: ContentStyle = ContentStyle.Default,
): TopAppBarStyle = TopAppBarStyleImpl(
    layout = layout,
    commonStyle = commonStyle,
    contentStyle = contentStyle,
)

private data class TopAppBarStyleImpl(
    override val layout: TopAppBarLayout,
    override val commonStyle: ComponentCommonStyle,
    override val contentStyle: ContentStyle,
) : TopAppBarStyle
