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
        minHeight += 40.dp
        minWidth += 40.dp
        padding += padding(horizontal = 24.dp, vertical = 2.dp)

        content {
            color += Colors.semantic.onInverseSurface
            fontSize += 18.sp
        }

        pressed {
            background += Colors.semantic.inverseSurfacePressed
        }
    }
}
