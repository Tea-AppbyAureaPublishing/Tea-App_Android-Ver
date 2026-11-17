package com.aureapubishing.teaApp.ui.screens.profile

import com.aureapublishing.teaApp.ui.theme.Primary500
import com.aureapublishing.teaApp.ui.theme.Secondary100
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.aureapubishing.teaApp.R


@Composable
fun ProfileScreen(
    onNext: () -> Unit
) {
    // Columna principal del contenido de Perfil.
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Contenido principal del perfil.
        ProfileContent()

        // Opciones para cambiar entre las secciones del perfil.
        ProfileOption()
    }
}


@Composable
fun ProfileContent() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        // Título principal del perfil.
        Text(
            text = "Mi perfil",
            fontSize = 30.sp,
            color = Color(0xFFF2C438)
        )

        // Espacio entre el título y la tarjeta.
        Spacer(modifier = Modifier.height(20.dp))

        // Tarjeta con los datos del usuario.
        ProfileUserCard()
    }
}


@Composable
fun ProfileUserCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Secondary100,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            // Imagen de perfil y círculo
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .background(
                        color = Color(0xFFB7C9B8),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.favicon),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Nombre del usuario
                Text(
                    text = "Nombre de usuario",
                    fontSize = 20.sp,
                    color = Primary500
                )

                Spacer(modifier = Modifier.height(5.dp))

                // Correo electrónico
                Text(
                    text = "correo@ejemplo.com",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Botón para editar el perfil
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary500
                    )
                ) {
                    Text(
                        text = "Editar perfil",
                        color = Secondary100
                    )
                }
            }

        }

    }
}


@Composable
fun ProfileOption() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {

        // Fila con los tres botones de las secciones.
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // Botón de tés favoritos.
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    horizontal = 4.dp,
                    vertical = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary100
                )
            ) {
                Text(
                    text = "Tés favoritos",
                    color = Primary500,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }

            // Botón de tés propios.
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    horizontal = 4.dp,
                    vertical = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary100
                )
            ) {
                Text(
                    text = "Tés propios",
                    color = Primary500,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }

            // Botón de pedidos.
            Button(
                onClick = { },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    horizontal = 4.dp,
                    vertical = 8.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary100
                )
            ) {
                Text(
                    text = "Pedidos",
                    color = Primary500,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}
