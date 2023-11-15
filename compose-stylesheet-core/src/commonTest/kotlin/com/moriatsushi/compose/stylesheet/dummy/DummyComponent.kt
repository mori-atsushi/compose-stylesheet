package com.moriatsushi.compose.stylesheet.dummy

import com.moriatsushi.compose.stylesheet.component.Component

val dummyComponent = Component(
    name = "SampleComponent",
    defaultStyle = DummyComponentStyle(),
    createBuilder = ::DummyComponentStyleBuilder,
)
