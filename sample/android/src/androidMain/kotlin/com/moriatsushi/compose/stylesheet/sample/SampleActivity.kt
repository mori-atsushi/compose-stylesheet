package com.moriatsushi.compose.stylesheet.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import com.moriatsushi.compose.stylesheet.ProvideStyleSheet

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProvideStyleSheet(
                styleSheet = createStyleSheet(useDarkMode = isSystemInDarkTheme()),
            ) {
                SampleScreen()
            }
        }
    }
}
