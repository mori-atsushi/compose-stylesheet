package com.moriatsushi.compose.stylesheet.component

@RequiresOptIn(message = "This API is for building a component.")
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.CONSTRUCTOR,
    AnnotationTarget.PROPERTY,
)
annotation class StyleSheetComponentApi
