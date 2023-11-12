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
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.ComponentStyleBuilder
import com.moriatsushi.compose.stylesheet.content.ContentStyle
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for [TextStyle].
 */
@StyleSheetBuilderMarker
class TextStyleBuilder internal constructor() : ComponentStyleBuilder<TextStyle>() {
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
    val textShadow: TokenSetter<Shadow?> = TokenSetter()
    val textDrawStyle: TokenSetter<DrawStyle?> = TokenSetter()
    val textAlign: TokenSetter<TextAlign?> = TokenSetter()
    val textDirection: TokenSetter<TextDirection?> = TokenSetter()
    val lineHeight: TokenSetter<TextUnit> = TokenSetter()
    val textIndent: TokenSetter<TextIndent?> = TokenSetter()
    val platformTextStyle: TokenSetter<PlatformTextStyle?> = TokenSetter()
    val lineHeightStyle: TokenSetter<LineHeightStyle?> = TokenSetter()
    val lineBreak: TokenSetter<LineBreak?> = TokenSetter()
    val hyphens: TokenSetter<Hyphens?> = TokenSetter()
    val textMotion: TokenSetter<TextMotion?> = TokenSetter()
    val overflow: TokenSetter<TextOverflow?> = TokenSetter()
    val softWrap: TokenSetter<Boolean?> = TokenSetter()
    val maxLines: TokenSetter<Int?> = TokenSetter()
    val minLines: TokenSetter<Int?> = TokenSetter()

    internal operator fun plusAssign(contentStyle: ContentStyle) {
        color += contentStyle.color
        fontSize += contentStyle.fontSize
        fontWeight += contentStyle.fontWeight
        fontStyle += contentStyle.fontStyle
        fontSynthesis += contentStyle.fontSynthesis
        fontFamily += contentStyle.fontFamily
        fontFeatureSettings += contentStyle.fontFeatureSettings
        letterSpacing += contentStyle.letterSpacing
        baselineShift += contentStyle.baselineShift
        textGeometricTransform += contentStyle.textGeometricTransform
        localeList += contentStyle.localeList
        textBackground += contentStyle.textBackground
        textDecoration += contentStyle.textDecoration
        textShadow += contentStyle.textShadow
        textDrawStyle += contentStyle.textDrawStyle
        textAlign += contentStyle.textAlign
        textDirection += contentStyle.textDirection
        lineHeight += contentStyle.lineHeight
        textIndent += contentStyle.textIndent
        platformTextStyle += contentStyle.platformTextStyle
        lineHeightStyle += contentStyle.lineHeightStyle
        lineBreak += contentStyle.lineBreak
        hyphens += contentStyle.hyphens
        textMotion += contentStyle.textMotion
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
        textShadow += other.textShadow
        textDrawStyle += other.textDrawStyle
        textAlign += other.textAlign
        textDirection += other.textDirection
        lineHeight += other.lineHeight
        textIndent += other.textIndent
        platformTextStyle += other.platformTextStyle
        lineHeightStyle += other.lineHeightStyle
        lineBreak += other.lineBreak
        hyphens += other.hyphens
        textMotion += other.textMotion
        overflow += other.overflow
        softWrap += other.softWrap
        maxLines += other.maxLines
        minLines += other.minLines
        this += other.commonStyle
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
        textShadow = textShadow.token,
        textDrawStyle = textDrawStyle.token,
        textAlign = textAlign.token,
        textDirection = textDirection.token,
        lineHeight = lineHeight.token,
        textIndent = textIndent.token,
        platformTextStyle = platformTextStyle.token,
        lineHeightStyle = lineHeightStyle.token,
        lineBreak = lineBreak.token,
        hyphens = hyphens.token,
        textMotion = textMotion.token,
        overflow = overflow.token,
        softWrap = softWrap.token,
        maxLines = maxLines.token,
        minLines = minLines.token,
        commonStyle = buildCommonStyle(),
    )
}
