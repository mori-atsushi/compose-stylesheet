package com.moriatsushi.compose.stylesheet.content

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
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
import com.moriatsushi.compose.stylesheet.token.Token

/**
 * A common style that can be applied to a component. This is provided by [LocalContentStyle] and
 * can be customized using [ProvideContentStyle].
 */
@Stable
sealed interface ContentStyle {
    val color: Token<Color>?
    val fontSize: Token<TextUnit>?
    val fontWeight: Token<FontWeight?>?
    val fontStyle: Token<FontStyle?>?
    val fontSynthesis: Token<FontSynthesis?>?
    val fontFamily: Token<FontFamily?>?
    val fontFeatureSettings: Token<String?>?
    val letterSpacing: Token<TextUnit>?
    val baselineShift: Token<BaselineShift?>?
    val textGeometricTransform: Token<TextGeometricTransform?>?
    val localeList: Token<LocaleList?>?
    val textBackground: Token<Color?>?
    val textDecoration: Token<TextDecoration?>?
    val textShadow: Token<Shadow?>?
    val textDrawStyle: Token<DrawStyle?>?
    val textAlign: Token<TextAlign?>?
    val textDirection: Token<TextDirection?>?
    val lineHeight: Token<TextUnit>?
    val textIndent: Token<TextIndent?>?
    val platformTextStyle: Token<PlatformTextStyle?>?
    val lineHeightStyle: Token<LineHeightStyle?>?
    val lineBreak: Token<LineBreak?>?
    val hyphens: Token<Hyphens?>?
    val textMotion: Token<TextMotion?>?

    companion object {
        /**
         * Constant for a default [ContentStyle].
         */
        val Default = ContentStyle()
    }
}

/**
 * Creates a new [ContentStyle] using the given [builder].
 */
fun ContentStyle(builder: ContentStyleBuilder.() -> Unit): ContentStyle =
    ContentStyleBuilder().apply(builder).build()

internal fun ContentStyle(
    color: Token<Color>? = null,
    fontSize: Token<TextUnit>? = null,
    fontWeight: Token<FontWeight?>? = null,
    fontStyle: Token<FontStyle?>? = null,
    fontSynthesis: Token<FontSynthesis?>? = null,
    fontFamily: Token<FontFamily?>? = null,
    fontFeatureSettings: Token<String?>? = null,
    letterSpacing: Token<TextUnit>? = null,
    baselineShift: Token<BaselineShift?>? = null,
    textGeometricTransform: Token<TextGeometricTransform?>? = null,
    localeList: Token<LocaleList?>? = null,
    textBackground: Token<Color?>? = null,
    textDecoration: Token<TextDecoration?>? = null,
    textShadow: Token<Shadow?>? = null,
    textDrawStyle: Token<DrawStyle?>? = null,
    textAlign: Token<TextAlign?>? = null,
    textDirection: Token<TextDirection?>? = null,
    lineHeight: Token<TextUnit>? = null,
    textIndent: Token<TextIndent?>? = null,
    platformTextStyle: Token<PlatformTextStyle?>? = null,
    lineHeightStyle: Token<LineHeightStyle?>? = null,
    lineBreak: Token<LineBreak?>? = null,
    hyphens: Token<Hyphens?>? = null,
    textMotion: Token<TextMotion?>? = null,
): ContentStyle = ContentStyleImpl(
    color = color,
    fontSize = fontSize,
    fontWeight = fontWeight,
    fontStyle = fontStyle,
    fontSynthesis = fontSynthesis,
    fontFamily = fontFamily,
    fontFeatureSettings = fontFeatureSettings,
    letterSpacing = letterSpacing,
    baselineShift = baselineShift,
    textGeometricTransform = textGeometricTransform,
    localeList = localeList,
    textBackground = textBackground,
    textDecoration = textDecoration,
    textShadow = textShadow,
    textDrawStyle = textDrawStyle,
    textAlign = textAlign,
    textDirection = textDirection,
    lineHeight = lineHeight,
    textIndent = textIndent,
    platformTextStyle = platformTextStyle,
    lineHeightStyle = lineHeightStyle,
    lineBreak = lineBreak,
    hyphens = hyphens,
    textMotion = textMotion,
)

@Immutable
private data class ContentStyleImpl(
    override val color: Token<Color>?,
    override val fontSize: Token<TextUnit>?,
    override val fontWeight: Token<FontWeight?>?,
    override val fontStyle: Token<FontStyle?>?,
    override val fontSynthesis: Token<FontSynthesis?>?,
    override val fontFamily: Token<FontFamily?>?,
    override val fontFeatureSettings: Token<String?>?,
    override val letterSpacing: Token<TextUnit>?,
    override val baselineShift: Token<BaselineShift?>?,
    override val textGeometricTransform: Token<TextGeometricTransform?>?,
    override val localeList: Token<LocaleList?>?,
    override val textBackground: Token<Color?>?,
    override val textDecoration: Token<TextDecoration?>?,
    override val textShadow: Token<Shadow?>?,
    override val textDrawStyle: Token<DrawStyle?>?,
    override val textAlign: Token<TextAlign?>?,
    override val textDirection: Token<TextDirection?>?,
    override val lineHeight: Token<TextUnit>?,
    override val textIndent: Token<TextIndent?>?,
    override val platformTextStyle: Token<PlatformTextStyle?>?,
    override val lineHeightStyle: Token<LineHeightStyle?>?,
    override val lineBreak: Token<LineBreak?>?,
    override val hyphens: Token<Hyphens?>?,
    override val textMotion: Token<TextMotion?>?,
) : ContentStyle
