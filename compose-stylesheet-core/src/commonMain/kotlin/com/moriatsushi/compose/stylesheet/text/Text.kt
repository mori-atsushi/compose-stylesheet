package com.moriatsushi.compose.stylesheet.text

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.moriatsushi.compose.stylesheet.ContentStyle
import com.moriatsushi.compose.stylesheet.LocalContentStyle
import com.moriatsushi.compose.stylesheet.StyleSheet
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
 * 3. The current [ContentStyle] from [LocalContentStyle].
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

        this.color += color
        this.fontSize += fontSize
        this.fontWeight += fontWeight
        this.fontStyle += fontStyle
        this.fontFamily += fontFamily
        this.letterSpacing += letterSpacing
        this.textDecoration += textDecoration
        this.textAlign += textAlign
        this.lineHeight += lineHeight
        this.overflow += overflow
        this.softWrap += softWrap
        this.maxLines += maxLines
        this.minLines += minLines
    }

    BasicText(
        text = text,
        modifier = modifier,
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
        shadow = shadow?.value,
        drawStyle = drawStyle?.value,
        textAlign = textAlign?.value,
        textDirection = textDirection?.value,
        lineHeight = lineHeight?.value ?: TextUnit.Unspecified,
        textIndent = textIndent?.value,
        platformStyle = platformStyle?.value,
        lineHeightStyle = lineHeightStyle?.value,
        lineBreak = lineBreak?.value,
        hyphens = hyphens?.value,
        textMotion = textMotion?.value,
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
