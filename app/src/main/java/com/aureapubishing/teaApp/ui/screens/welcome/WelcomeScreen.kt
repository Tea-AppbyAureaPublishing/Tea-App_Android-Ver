
package com.aureapubishing.teaApp.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.components.AppBackground

// Colores de la pantalla
private val Cream = Color(0xFFFFF8E1)
private val DarkGreen = Color(0xFF263D21)
private val Green = Color(0xFF485C43)
private val Yellow = Color(0xFFE0BB53)

// Pantalla principal de bienvenida

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Parte superior: título y logo
            WelcomeHeader()

            // Espacio flexible para empujar los botones hacia abajo
            Spacer(modifier = Modifier.weight(1f))

            // Parte inferior: botones de acceso
            WelcomeButtons(
                onLogin = onLogin,
                onRegister = onRegister
            )
        }
    }
}


// Muestra el título y el logo de la aplicación
@Composable
private fun WelcomeHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Tea-App",
            fontSize = 30.sp,
            color = Cream,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.favicon),
            contentDescription = "Logo de Tea-App",
            modifier = Modifier.size(130.dp)
        )
    }
}

// Organiza los dos botones de acceso
@Composable
private fun WelcomeButtons(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LoginButton(onClick = onLogin)

        RegisterButton(onClick = onRegister)
    }
}

// Botón para iniciar sesión
@Composable
private fun LoginButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Yellow,
            contentColor = DarkGreen
        )
    ) {
        Text(
            text = "Iniciar sesión",
            fontSize = 14.sp
        )
    }
}

// Botón para crear una cuenta
@Composable
private fun RegisterButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Green,
            contentColor = Cream
        )
    ) {
        Text(
            text = "Crear cuenta",
            fontSize = 14.sp
        )
    }
}
