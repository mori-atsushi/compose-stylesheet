package com.moriatsushi.compose.stylesheet.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.content.ProvideContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier

/**
 * A button component.
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tags: TagModifier<ButtonStyle> = TagModifier(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    buttonStyle: ButtonStyle = ButtonStyle.Default,
    content: @Composable RowScope.() -> Unit,
) {
    val localStyle = StyleSheet.getStyle(button, tags)
    val mergedStyle = ButtonStyle {
        this += localStyle
        this += buttonStyle
    }

    Row(
        modifier = modifier
            .semantics { role = Role.Button }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .componentCommonStyle(mergedStyle.commonStyle),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProvideContentStyle(contentStyle = mergedStyle.contentStyle) {
            content()
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
