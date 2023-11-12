package com.moriatsushi.compose.stylesheet.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moriatsushi.compose.stylesheet.surface.Surface
import com.moriatsushi.compose.stylesheet.text.Text

@Composable
fun SampleScreen(
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("Hello, Compose StyleSheet!")
        }
    }
}
