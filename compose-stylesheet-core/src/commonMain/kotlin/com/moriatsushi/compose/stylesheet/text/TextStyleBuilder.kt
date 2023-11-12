package com.moriatsushi.compose.stylesheet.text

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [TextStyle].
 */
@StyleSheetBuilderMarker
class TextStyleBuilder internal constructor() : StyleBuilder<TextStyle> {
    val color: TokenSetter<Color> = TokenSetter()
    val fontSize: TokenSetter<TextUnit> = TokenSetter()
    val fontWeight: TokenSetter<FontWeight?> = TokenSetter()
    val fontStyle: TokenSetter<FontStyle?> = TokenSetter()
    val fontSynthesis: TokenSetter<FontSynthesis?> = TokenSetter()
    val fontFamily: TokenSetter<FontFamily?> = TokenSetter()
    val fontFeatureSettings: TokenSetter<String?> = TokenSetter()
    val letterSpacing: TokenSetter<TextUnit> = TokenSetter()
    val baselineShift: TokenSetter<BaselineShift?> = TokenSetter()
    val textGeometricTransform: TokenSetter<TextGeometricTransform?> = TokenSetter()
    val localeList: TokenSetter<LocaleList?> = TokenSetter()
    val textBackground: TokenSetter<Color?> = TokenSetter()
    val textDecoration: TokenSetter<TextDecoration?> = TokenSetter()
    val shadow: TokenSetter<Shadow?> = TokenSetter()
    val drawStyle: TokenSetter<DrawStyle?> = TokenSetter()
    val textAlign: TokenSetter<TextAlign?> = TokenSetter()
    val textDirection: TokenSetter<TextDirection?> = TokenSetter()
    val lineHeight: TokenSetter<TextUnit> = TokenSetter()
    val textIndent: TokenSetter<TextIndent?> = TokenSetter()
    val platformStyle: TokenSetter<PlatformTextStyle?> = TokenSetter()
    val lineHeightStyle: TokenSetter<LineHeightStyle?> = TokenSetter()
    val lineBreak: TokenSetter<LineBreak?> = TokenSetter()
    val hyphens: TokenSetter<Hyphens?> = TokenSetter()
    val textMotion: TokenSetter<TextMotion?> = TokenSetter()
    val overflow: TokenSetter<TextOverflow?> = TokenSetter()
    val softWrap: TokenSetter<Boolean?> = TokenSetter()
    val maxLines: TokenSetter<Int?> = TokenSetter()
    val minLines: TokenSetter<Int?> = TokenSetter()
    val background: TokenSetter<Color> = TokenSetter()

    internal operator fun plusAssign(contentStyle: ContentStyle) {
        color += contentStyle.color
    }

    override fun plusAssign(other: TextStyle) {
        color += other.color
        fontSize += other.fontSize
        fontWeight += other.fontWeight
        fontStyle += other.fontStyle
        fontSynthesis += other.fontSynthesis
        fontFamily += other.fontFamily
        fontFeatureSettings += other.fontFeatureSettings
        letterSpacing += other.letterSpacing
        baselineShift += other.baselineShift
        textGeometricTransform += other.textGeometricTransform
        localeList += other.localeList
        textBackground += other.textBackground
        textDecoration += other.textDecoration
        shadow += other.shadow
        drawStyle += other.drawStyle
        textAlign += other.textAlign
        textDirection += other.textDirection
        lineHeight += other.lineHeight
        textIndent += other.textIndent
        platformStyle += other.platformStyle
        lineHeightStyle += other.lineHeightStyle
        lineBreak += other.lineBreak
        hyphens += other.hyphens
        textMotion += other.textMotion
        overflow += other.overflow
        softWrap += other.softWrap
        maxLines += other.maxLines
        minLines += other.minLines
        background += other.background
    }

    override fun build(): TextStyle = TextStyle(
        color = color.token,
        fontSize = fontSize.token,
        fontWeight = fontWeight.token,
        fontStyle = fontStyle.token,
        fontSynthesis = fontSynthesis.token,
        fontFamily = fontFamily.token,
        fontFeatureSettings = fontFeatureSettings.token,
        letterSpacing = letterSpacing.token,
        baselineShift = baselineShift.token,
        textGeometricTransform = textGeometricTransform.token,
        localeList = localeList.token,
        textBackground = textBackground.token,
        textDecoration = textDecoration.token,
        shadow = shadow.token,
        drawStyle = drawStyle.token,
        textAlign = textAlign.token,
        textDirection = textDirection.token,
        lineHeight = lineHeight.token,
        textIndent = textIndent.token,
        platformStyle = platformStyle.token,
        lineHeightStyle = lineHeightStyle.token,
        lineBreak = lineBreak.token,
        hyphens = hyphens.token,
        textMotion = textMotion.token,
        overflow = overflow.token,
        softWrap = softWrap.token,
        maxLines = maxLines.token,
        minLines = minLines.token,
        background = background.token,
    )
}
