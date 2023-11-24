package com.moriatsushi.compose.stylesheet.theme.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.button.iconButton
import com.moriatsushi.compose.stylesheet.theme.Colors

internal val iconButtonStyleSheet = StyleSheet {
    iconButton {
        color += Colors.semantic.onBackground
        size += 40.dp
        shape += CircleShape

        pressed {
            color += Colors.semantic.onBackgroundPressed
        }

        hovered {
            color += Colors.semantic.onBackgroundHovered
        }

        focused {
            outline.width += 3.dp
            outline.color += Colors.semantic.focus
        }

        disabled {
            color += Colors.semantic.onBackgroundDisabled
        }
    }
}
