package com.moriatsushi.compose.stylesheet.appbar

import androidx.compose.ui.unit.Dp
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [TopAppBarLayout].
 */
class TopAppBarLayoutBuilder internal constructor() : StyleBuilder<TopAppBarLayout> {
    val spaceBetweenNavigationIconAndContainer: TokenSetter<Dp> = TokenSetter()
    val spaceBetweenNavigationIconAndTitle: TokenSetter<Dp> = TokenSetter()
    val spaceBetweenTitleAndContainer: TokenSetter<Dp> = TokenSetter()
    val spaceBetweenActionsAndContent: TokenSetter<Dp> = TokenSetter()
    val spaceBetweenActionsAndContainer: TokenSetter<Dp> = TokenSetter()

    override fun plusAssign(other: TopAppBarLayout) {
        spaceBetweenNavigationIconAndContainer += other.spaceBetweenNavigationIconAndContainer
        spaceBetweenNavigationIconAndTitle += other.spaceBetweenNavigationIconAndTitle
        spaceBetweenTitleAndContainer += other.spaceBetweenTitleAndContainer
        spaceBetweenActionsAndContent += other.spaceBetweenActionsAndContent
        spaceBetweenActionsAndContainer += other.spaceBetweenActionsAndContainer
    }

    operator fun invoke(builder: TopAppBarLayoutBuilder.() -> Unit) {
        builder()
    }

    override fun build(): TopAppBarLayout = TopAppBarLayout(
        spaceBetweenNavigationIconAndContainer = spaceBetweenNavigationIconAndContainer.token,
        spaceBetweenNavigationIconAndTitle = spaceBetweenNavigationIconAndTitle.token,
        spaceBetweenTitleAndContainer = spaceBetweenTitleAndContainer.token,
        spaceBetweenActionsAndContent = spaceBetweenActionsAndContent.token,
        spaceBetweenActionsAndContainer = spaceBetweenActionsAndContainer.token,
    )
}
