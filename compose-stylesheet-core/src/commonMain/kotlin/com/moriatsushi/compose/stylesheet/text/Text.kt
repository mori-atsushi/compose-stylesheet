package com.moriatsushi.compose.stylesheet.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.LocalContentStyle
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.color.asColor
import com.moriatsushi.compose.stylesheet.tag.Tag

/**
 * An element that displays text.
 */
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    tag: Tag<TextStyle, TextStyleBuilder>? = null,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val textColor = color
        .takeOrElse { localTextStyle(tag).color.asColor() }
        .takeOrElse { LocalContentStyle.current.color.asColor() }

    BasicText(
        text = text,
        modifier = modifier,
        style = ComposeTextStyle(
            color = textColor,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
        ),
        onTextLayout = onTextLayout,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
    )
}

@Composable
private fun localTextStyle(tag: Tag<TextStyle, TextStyleBuilder>?): TextStyle =
    StyleSheet.getStyle(text, tag)

/**
 * A symbol for [Text].
 */
val text = Component(
    name = "Text",
    defaultStyle = TextStyle(),
    createBuilder = ::TextStyleBuilder,
)
