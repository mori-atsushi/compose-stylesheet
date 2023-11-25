package com.moriatsushi.compose.stylesheet.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.compose.stylesheet.appbar.TopAppBar
import com.moriatsushi.compose.stylesheet.button.Button
import com.moriatsushi.compose.stylesheet.button.IconButton
import com.moriatsushi.compose.stylesheet.divider.Divider
import com.moriatsushi.compose.stylesheet.icon.Icon
import com.moriatsushi.compose.stylesheet.surface.Surface
import com.moriatsushi.compose.stylesheet.text.Text
import com.moriatsushi.compose.stylesheet.theme.surface.background

@Composable
fun SampleScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        tags = Surface.background,
        modifier = modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Compose StyleSheet") },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Outlined.Settings, null)
                    }
                },
            )
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Compose StyleSheet is a flexible UI component framework " +
                        "for Jetpack Compose",
                    tags = TextTags.primary,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "This library is still under development. " +
                        "All APIs may change in the future.",
                    tags = TextTags.secondary,
                )
                Divider(modifier = Modifier.height(64.dp))
                Button(
                    onClick = {},
                    icon = { Icon(Icons.Default.Rocket, null) },
                ) {
                    Text(text = "Start!")
                }
            }
        }
    }
}
