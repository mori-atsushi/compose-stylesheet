package com.moriatsushi.compose.stylesheet.text

import androidx.compose.foundation.background
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
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.content.LocalContentStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier
import com.moriatsushi.compose.stylesheet.token.value

/**
 * An element that displays text.
 *
 * The style is applied in the following order:
 *
 * 1. Specified arguments to this function, such as [color], [fontSize], etc.
 * 2. The specified [textStyle] argument to this function.
 * 3. Styles specified by [tags].
 * 4. The current [TextStyle] from [StyleSheet].
 * 5. The current [ContentStyle] from [LocalContentStyle].
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
    textStyle: TextStyle = TextStyle.Default,
) {
    val localTextStyle = localTextStyle(tags)
    val localContentStyle = LocalContentStyle.current

    val mergedTextStyle = TextStyle {
        this += localContentStyle
        this += localTextStyle
        this += textStyle

        if (color.isSpecified) {
            this.color += color
        }
        if (fontSize.isSpecified) {
            this.fontSize += fontSize
        }
        if (fontWeight != null) {
            this.fontWeight += fontWeight
        }
        if (fontStyle != null) {
            this.fontStyle += fontStyle
        }
        if (fontFamily != null) {
            this.fontFamily += fontFamily
        }
        if (letterSpacing.isSpecified) {
            this.letterSpacing += letterSpacing
        }
        if (textDecoration != null) {
            this.textDecoration += textDecoration
        }
        if (textAlign != null) {
            this.textAlign += textAlign
        }
        if (lineHeight.isSpecified) {
            this.lineHeight += lineHeight
        }
        if (overflow != null) {
            this.overflow += overflow
        }
        if (softWrap != null) {
            this.softWrap += softWrap
        }
        if (maxLines != null) {
            this.maxLines += maxLines
        }
        if (minLines != null) {
            this.minLines += minLines
        }
    }

    BasicText(
        text = text,
        modifier = modifier.componentCommonStyle(mergedTextStyle.commonStyle),
        style = mergedTextStyle.composeTextStyle,
        onTextLayout = onTextLayout,
        overflow = mergedTextStyle.overflow?.value ?: TextOverflow.Clip,
        softWrap = mergedTextStyle.softWrap?.value ?: true,
        maxLines = mergedTextStyle.maxLines?.value ?: Int.MAX_VALUE,
        minLines = mergedTextStyle.minLines?.value ?: 1,
    )
}

private val TextStyle.composeTextStyle: ComposeTextStyle
    @Composable
    get() = ComposeTextStyle(
        color = color?.value ?: Color.Unspecified,
        fontSize = fontSize?.value ?: TextUnit.Unspecified,
        fontWeight = fontWeight?.value,
        fontStyle = fontStyle?.value,
        fontSynthesis = fontSynthesis?.value,
        fontFamily = fontFamily?.value,
        fontFeatureSettings = fontFeatureSettings?.value,
        letterSpacing = letterSpacing?.value ?: TextUnit.Unspecified,
        baselineShift = baselineShift?.value,
        textGeometricTransform = textGeometricTransform?.value,
        localeList = localeList?.value,
        background = textBackground?.value ?: Color.Unspecified,
        textDecoration = textDecoration?.value,
        shadow = textShadow?.value,
        drawStyle = textDrawStyle?.value,
        textAlign = textAlign?.value,
        textDirection = textDirection?.value,
        lineHeight = lineHeight?.value ?: TextUnit.Unspecified,
        textIndent = textIndent?.value,
        platformStyle = platformTextStyle?.value,
        lineHeightStyle = lineHeightStyle?.value,
        lineBreak = lineBreak?.value,
        hyphens = hyphens?.value,
        textMotion = textMotion?.value,
    )

private fun Modifier.background(color: Color?): Modifier =
    if (color != null) background(color) else this

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
