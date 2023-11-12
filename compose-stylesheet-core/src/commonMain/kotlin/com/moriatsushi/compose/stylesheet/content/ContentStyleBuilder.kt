package com.moriatsushi.compose.stylesheet.content

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
import androidx.compose.ui.unit.TextUnit
import com.moriatsushi.compose.stylesheet.StyleBuilder
import com.moriatsushi.compose.stylesheet.StyleSheetBuilderMarker
import com.moriatsushi.compose.stylesheet.component.StylesheetComponentApi
import com.moriatsushi.compose.stylesheet.token.TokenSetter

/**
 * A builder for common styles in [com.moriatsushi.compose.stylesheet.StyleSheet].
 */
@StyleSheetBuilderMarker
class ContentStyleBuilder @StylesheetComponentApi constructor() : StyleBuilder<ContentStyle> {
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

    override fun plusAssign(other: ContentStyle) {
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
    }

    override fun build(): ContentStyle = ContentStyle(
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
    )
}
