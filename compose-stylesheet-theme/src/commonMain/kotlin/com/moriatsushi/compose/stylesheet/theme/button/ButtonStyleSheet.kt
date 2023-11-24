package com.moriatsushi.compose.stylesheet.theme.button

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.button.button
import com.moriatsushi.compose.stylesheet.theme.Colors

internal val buttonStyleSheet = StyleSheet {
    button {
        background += Colors.semantic.inverseSurface
        shape += RoundedCornerShape(50)
        minSize += 40.dp
        padding += padding(vertical = 2.dp)

        layout {
            spaceBetweenContentAndContainer += 24.dp
            spaceBetweenIconAndContent += 8.dp
            spaceBetweenIconAndContainer += 16.dp
        }

        content {
            color += Colors.semantic.onInverseSurface
            fontSize += 18.sp
        }

        pressed {
            background += Colors.semantic.inverseSurfacePressed
        }

        hovered {
            background += Colors.semantic.inverseSurfaceHovered
        }

        focused {
            outline.width += 3.dp
            outline.color += Colors.semantic.focus
        }

        disabled {
            background += Colors.semantic.disabledSurface
            content.color += Colors.semantic.onDisabledSurface
        }
    }
}
