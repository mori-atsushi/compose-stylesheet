package com.moriatsushi.compose.stylesheet.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
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
import com.moriatsushi.compose.stylesheet.token.value

/**
 * A button component.
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    tags: TagModifier<ButtonStyle> = TagModifier(),
    iconPosition: ButtonIconPosition? = null,
    buttonStyle: ButtonStyle = ButtonStyle.Default,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    icon: @Composable (() -> Unit)? = null,
    content: @Composable RowScope.() -> Unit,
) {
    val localStyle = StyleSheet.getStyle(button, tags)
    val mergedStyle = ButtonStyle {
        this += localStyle
        this += buttonStyle
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val stateStyle = ButtonStateStyle {
        this += mergedStyle.getStyleForState(
            isEnabled = enabled,
            isPressed = isPressed,
            isHovered = isHovered,
            isFocused = isFocused,
        )

        layout.iconPosition += iconPosition
    }

    ButtonContent(
        modifier = modifier
            .semantics { role = Role.Button }
            .componentCommonStyle(stateStyle.commonStyle, includePadding = false)
            .clickable(
                interactionSource = interactionSource,
                indication = stateStyle.indication?.value,
                onClick = onClick,
                enabled = enabled,
            )
            .componentPadding(stateStyle.commonStyle.padding),
        contentStyle = stateStyle.contentStyle,
        icon = icon,
        layout = stateStyle.layout,
        content = content,
    )
}

@Composable
private fun ButtonContent(
    contentStyle: ContentStyle,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
    layout: ButtonLayout = ButtonLayout.Default,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val iconPosition = layout.iconPosition ?: ButtonIconPosition.Start
        val spaceBetweenIconAndContainer = layout.spaceBetweenIconAndContainer?.value
        val spaceBetweenIconAndContent = layout.spaceBetweenIconAndContent?.value
        val spaceBetweenContentAndContainer = layout.spaceBetweenContentAndContainer?.value

        ProvideContentStyle(contentStyle = contentStyle) {
            if (icon != null && iconPosition == ButtonIconPosition.Start) {
                if (spaceBetweenIconAndContainer != null) {
                    Spacer(modifier = Modifier.width(spaceBetweenIconAndContainer))
                }
                icon()
                if (spaceBetweenIconAndContent != null) {
                    Spacer(modifier = Modifier.width(spaceBetweenIconAndContent))
                }
            } else if (spaceBetweenContentAndContainer != null) {
                Spacer(modifier = Modifier.width(spaceBetweenContentAndContainer))
            }

            content()

            if (icon != null && iconPosition == ButtonIconPosition.End) {
                if (spaceBetweenIconAndContent != null) {
                    Spacer(modifier = Modifier.width(spaceBetweenIconAndContent))
                }
                icon()
                if (spaceBetweenIconAndContainer != null) {
                    Spacer(modifier = Modifier.width(spaceBetweenIconAndContainer))
                }
            } else if (spaceBetweenContentAndContainer != null) {
                Spacer(modifier = Modifier.width(spaceBetweenContentAndContainer))
            }
        }
    }
}

/**
 * An object for [Button].
 */
object Button

/**
 * A symbol for [Button].
 */
val button = Component(
    name = "Button",
    defaultStyle = ButtonStyle.Default,
    createBuilder = ::ButtonStyleBuilder,
)
