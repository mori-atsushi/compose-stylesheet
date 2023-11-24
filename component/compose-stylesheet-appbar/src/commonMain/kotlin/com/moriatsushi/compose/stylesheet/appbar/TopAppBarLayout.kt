package com.moriatsushi.compose.stylesheet.appbar

import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A layout properties for [TopAppBar].
 */
sealed interface TopAppBarLayout {
    val spaceBetweenNavigationIconAndContainer: Token<Dp>?
    val spaceBetweenNavigationIconAndTitle: Token<Dp>?
    val spaceBetweenTitleAndContainer: Token<Dp>?
    val spaceBetweenActionsAndContent: Token<Dp>?
    val spaceBetweenActionsAndContainer: Token<Dp>?

    companion object {
        /**
         * Constant for a default [TopAppBarLayout].
         */
        val Default: TopAppBarLayout = TopAppBarLayout()
    }
}

internal fun TopAppBarLayout(
    spaceBetweenNavigationIconAndContainer: Token<Dp>? = null,
    spaceBetweenNavigationIconAndTitle: Token<Dp>? = null,
    spaceBetweenTitleAndContainer: Token<Dp>? = null,
    spaceBetweenActionsAndContent: Token<Dp>? = null,
    spaceBetweenActionsAndContainer: Token<Dp>? = null,
): TopAppBarLayout = TopAppBarLayoutImpl(
    spaceBetweenNavigationIconAndContainer = spaceBetweenNavigationIconAndContainer,
    spaceBetweenNavigationIconAndTitle = spaceBetweenNavigationIconAndTitle,
    spaceBetweenTitleAndContainer = spaceBetweenTitleAndContainer,
    spaceBetweenActionsAndContent = spaceBetweenActionsAndContent,
    spaceBetweenActionsAndContainer = spaceBetweenActionsAndContainer,
)

private data class TopAppBarLayoutImpl(
    override val spaceBetweenNavigationIconAndContainer: Token<Dp>?,
    override val spaceBetweenNavigationIconAndTitle: Token<Dp>?,
    override val spaceBetweenTitleAndContainer: Token<Dp>?,
    override val spaceBetweenActionsAndContent: Token<Dp>?,
    override val spaceBetweenActionsAndContainer: Token<Dp>?,
) : TopAppBarLayout
