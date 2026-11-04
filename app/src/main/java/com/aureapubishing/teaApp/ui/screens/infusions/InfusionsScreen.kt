package com.aureapubishing.teaApp.ui.screens.infusions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.aureapubishing.teaApp.R

@Composable
fun InfusionsScreen(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Infusiones: Funciona")
        // Imagen()

    }
}

/* Funcion para añadir una imagen
@Composable
fun Imagen(){
    Image(
        painter = painterResource( id = R.drawable.benefits),
        contentDescription = "sdnfjkahsufhauo"
    )
}
*/