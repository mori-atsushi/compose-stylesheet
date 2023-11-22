package com.moriatsushi.compose.stylesheet.theme.surface

import com.moriatsushi.compose.stylesheet.surface.Surface
import com.moriatsushi.compose.stylesheet.surface.SurfaceStyle
import com.moriatsushi.compose.stylesheet.surface.surface
import com.moriatsushi.compose.stylesheet.tag.Tag

/**
 * A tag for [Surface] that represents the background.
 */
@Suppress("UnusedReceiverParameter")
val Surface.background: Tag<SurfaceStyle> get() = backgroundTag

/**
 * A tag for [Surface] that represents the primary surface.
 */
@Suppress("UnusedReceiverParameter")
val Surface.primary: Tag<SurfaceStyle> get() = primaryTag

private val backgroundTag = Tag("background", surface)
private val primaryTag = Tag("primary", surface)
