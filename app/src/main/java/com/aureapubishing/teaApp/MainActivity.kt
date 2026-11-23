package com.aureapubishing.teaApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aureapubishing.teaApp.ui.navigation.MainScreen
import com.aureapublishing.teaApp.ui.theme.TeaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.enableEdgeToEdge

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            TeaAppTheme {
                MainScreen()
            }
        }
    }
}