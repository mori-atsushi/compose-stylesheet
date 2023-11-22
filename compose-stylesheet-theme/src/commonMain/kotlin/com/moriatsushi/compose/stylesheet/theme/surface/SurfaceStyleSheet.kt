package com.moriatsushi.compose.stylesheet.theme.surface

import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.surface.Surface
import com.moriatsushi.compose.stylesheet.surface.surface
import com.moriatsushi.compose.stylesheet.theme.Colors

internal val surfaceStyleSheet = StyleSheet {
    surface {
        background += Colors.semantic.primarySurface
        content.color += Colors.semantic.onPrimarySurface
    }

    surface(Surface.background) {
        background += Colors.semantic.background
        content.color += Colors.semantic.onBackground
    }

    surface(Surface.primary) {
        background += Colors.semantic.primarySurface
        content.color += Colors.semantic.onPrimarySurface
    }
}
