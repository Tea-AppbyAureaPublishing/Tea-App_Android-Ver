
package com.aureapubishing.teaApp.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onNext: () -> Unit
) {
    HomeContent()
}

@Composable
fun HomeContent() {
    Text("Contenido")
}
