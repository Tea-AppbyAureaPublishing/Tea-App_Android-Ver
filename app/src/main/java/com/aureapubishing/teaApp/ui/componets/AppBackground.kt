package com.aureapubishing.teaApp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R

@Composable
fun AppBackground(
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clipToBounds(),
        contentAlignment = Alignment.TopStart // He cambiado esta línea para que empiece arriba del todo
    ) {
        // Añadimos + 2 para asegurar que cubra toda la pantalla sin huecos al redondear
        val columns = (maxWidth / 64.dp).toInt() + 2
        val rows = (maxHeight / 61.dp).toInt() + 2

        Column(
            modifier = Modifier
                // Evita que Compose aplaste la última fila si sobra altura
                .wrapContentHeight(align = Alignment.Top, unbounded = true),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            repeat(rows) {
                Row(
                    modifier = Modifier
                        // Evita que Compose aplaste el último icono de la derecha
                        .wrapContentWidth(align = Alignment.Start, unbounded = true),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(columns) {
                        Image(
                            painter = painterResource(R.drawable.background),
                            contentDescription = null,
                            modifier = Modifier
                                .size(56.dp)
                                .alpha(0.15f),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface) // Secondary100
                        )
                    }
                }
            }
        }

        content()
    }
}
