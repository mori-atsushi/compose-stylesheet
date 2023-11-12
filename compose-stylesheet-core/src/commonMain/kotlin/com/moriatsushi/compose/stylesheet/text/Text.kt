package com.moriatsushi.compose.stylesheet.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle as ComposeTextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
import com.moriatsushi.compose.stylesheet.Component
import com.moriatsushi.compose.stylesheet.ContentStyle
import com.moriatsushi.compose.stylesheet.LocalContentStyle
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An element that displays text.
 */
@Composable
fun Text(
    text: String,
    modifier: Modifier = Modifier,
    tags: TagModifier<TextStyle> = TagModifier(),
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow? = null,
    softWrap: Boolean? = null,
    maxLines: Int? = null,
    minLines: Int? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val localTextStyle = localTextStyle(tags)

    BasicText(
        text = text,
        modifier = modifier,
        style = composeTextStyle(
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            localTextStyle = localTextStyle,
            localContentStyle = LocalContentStyle.current,
        ),
        onTextLayout = onTextLayout,
        overflow = overflow
            ?: localTextStyle.overflow?.value
            ?: TextOverflow.Clip,
        softWrap = softWrap
            ?: localTextStyle.softWrap?.value
            ?: true,
        maxLines = maxLines
            ?: localTextStyle.maxLines?.value
            ?: Int.MAX_VALUE,
        minLines = minLines
            ?: localTextStyle.minLines?.value
            ?: 1,
    )
}

@Composable
private fun composeTextStyle(
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    localTextStyle: TextStyle,
    localContentStyle: ContentStyle,
): ComposeTextStyle = ComposeTextStyle(
    color = color.takeIf { it.isSpecified }
        ?: localTextStyle.color?.value
        ?: localContentStyle.color?.value
        ?: Color.Unspecified,
    fontSize = fontSize.takeIf { it.isSpecified }
        ?: localTextStyle.fontSize?.value
        ?: TextUnit.Unspecified,
    fontWeight = fontWeight ?: localTextStyle.fontWeight?.value,
    fontStyle = fontStyle ?: localTextStyle.fontStyle?.value,
    fontSynthesis = localTextStyle.fontSynthesis?.value,
    fontFamily = fontFamily ?: localTextStyle.fontFamily?.value,
    fontFeatureSettings = localTextStyle.fontFeatureSettings?.value,
    letterSpacing = letterSpacing.takeIf { it.isSpecified }
        ?: localTextStyle.letterSpacing?.value
        ?: TextUnit.Unspecified,
    baselineShift = localTextStyle.baselineShift?.value,
    textGeometricTransform = localTextStyle.textGeometricTransform?.value,
    localeList = localTextStyle.localeList?.value,
    background = localTextStyle.textBackground?.value ?: Color.Unspecified,
    textDecoration = textDecoration ?: localTextStyle.textDecoration?.value,
    shadow = localTextStyle.shadow?.value,
    drawStyle = localTextStyle.drawStyle?.value,
    textAlign = textAlign ?: localTextStyle.textAlign?.value,
    textDirection = localTextStyle.textDirection?.value,
    lineHeight = lineHeight.takeIf { it.isSpecified }
        ?: localTextStyle.lineHeight?.value
        ?: TextUnit.Unspecified,
    textIndent = localTextStyle.textIndent?.value,
    platformStyle = localTextStyle.platformStyle?.value,
    lineHeightStyle = localTextStyle.lineHeightStyle?.value,
    lineBreak = localTextStyle.lineBreak?.value,
    hyphens = localTextStyle.hyphens?.value,
    textMotion = localTextStyle.textMotion?.value,
)

@Composable
private fun localTextStyle(tags: TagModifier<TextStyle>): TextStyle =
    StyleSheet.getStyle(text, tags)

/**
 * A symbol for [Text].
 */
val text = Component(
    name = "Text",
    defaultStyle = TextStyle(),
    createBuilder = ::TextStyleBuilder,
)
