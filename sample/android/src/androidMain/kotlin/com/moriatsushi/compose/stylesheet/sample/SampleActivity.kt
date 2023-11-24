package com.moriatsushi.compose.stylesheet.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.moriatsushi.compose.stylesheet.ProvideStyleSheet

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            ProvideStyleSheet(styleSheet()) {
                SampleScreen()
            }
        }
    }
}
