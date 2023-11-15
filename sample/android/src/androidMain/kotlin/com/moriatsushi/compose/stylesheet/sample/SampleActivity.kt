package com.moriatsushi.compose.stylesheet.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.moriatsushi.compose.stylesheet.ProvideStyleSheet

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProvideStyleSheet(styleSheet()) {
                SampleScreen()
            }
        }
    }
}
