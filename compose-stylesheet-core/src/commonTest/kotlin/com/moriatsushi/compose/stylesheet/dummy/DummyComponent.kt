package com.moriatsushi.compose.stylesheet.dummy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.StyleSheet
import com.moriatsushi.compose.stylesheet.component.Component
import com.moriatsushi.compose.stylesheet.component.componentCommonStyle
import com.moriatsushi.compose.stylesheet.tag.TagModifier

@Composable
fun DummyComponent(
    modifier: Modifier = Modifier,
    tags: TagModifier<DummyComponentStyle> = TagModifier(),
    style: DummyComponentStyle = DummyComponentStyle.Default,
) {
    val localStyle = StyleSheet.getStyle(dummyComponent, tags)

    val mergedStyle = DummyComponentStyle {
        this += localStyle
        this += style
    }

    Box(modifier = modifier.componentCommonStyle(mergedStyle.commonStyle)) {
        Box(Modifier.size(10.dp))
    }
}

val dummyComponent = Component(
    name = "SampleComponent",
    defaultStyle = DummyComponentStyle(),
    createBuilder = ::DummyComponentStyleBuilder,
)
