package com.aureapubishing.teaApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.aureapubishing.teaApp.ui.navigation.MainScreen
import com.aureapublishing.teaApp.ui.theme.TeaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeaAppTheme {
                MainScreen()
            }
        }
    }
}