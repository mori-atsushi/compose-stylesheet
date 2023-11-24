package com.moriatsushi.compose.stylesheet.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.component.padding.componentPadding
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.content.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier

/**
 * A button with an icon.
 */
@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tags: TagModifier<IconButtonStyle> = TagModifier(),
    iconButtonStyle: IconButtonStyle = IconButtonStyle.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    icon: @Composable () -> Unit,
) {
    val localStyle = StyleSheet.getStyle(iconButton, tags)
    val mergedStyle = IconButtonStyle {
        this += localStyle
        this += iconButtonStyle
    }

    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val stateStyle = mergedStyle.getStyleForState(
        isEnabled = enabled,
        isPressed = isPressed,
        isHovered = isHovered,
        isFocused = isFocused,
    )

    val contentStyle = ContentStyle { color += stateStyle.color }

    Box(
        modifier = modifier
            .semantics { role = Role.Button }
            .componentCommonStyle(stateStyle.commonStyle, includePadding = false)
            .clickable(
                interactionSource = interactionSource,
                indication = mergedStyle.indication?.value,
                onClick = onClick,
                enabled = enabled,
            )
            .componentPadding(stateStyle.commonStyle.padding),
        contentAlignment = Alignment.Center,
    ) {
        ProvideContentStyle(contentStyle, content = icon)
    }
}

/**
 * An object for [IconButton].
 */
object IconButton

/**
 * A symbol for [IconButton].
 */
val iconButton = Component(
    name = "IconButton",
    defaultStyle = IconButtonStyle.Default,
    createBuilder = ::IconButtonStyleBuilder,
)
