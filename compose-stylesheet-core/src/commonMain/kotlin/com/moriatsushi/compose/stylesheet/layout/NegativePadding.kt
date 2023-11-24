package com.moriatsushi.compose.stylesheet.layout

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

internal fun Modifier.negativePadding(all: Dp): Modifier =
    this.layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val offset = all.roundToPx()
        layout(
            placeable.width - offset * 2,
            placeable.height - offset * 2,
        ) {
            placeable.place(-offset, -offset)
        }
    }
