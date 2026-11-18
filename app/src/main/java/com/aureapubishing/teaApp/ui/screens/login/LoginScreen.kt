
package com.aureapubishing.teaApp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.components.AppBackground

// Colores de la pantalla
private val Cream = Color(0xFFFFF8E1)
private val DarkGreen = Color(0xFF2A3225)
private val Green = Color(0xFF485C43)

// Pantalla principal de inicio de sesión
@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onLoggedIn: () -> Unit,
    onRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    AppBackground {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = R.drawable.favicon),
                contentDescription = "Logo de Tea-App",
                modifier = Modifier.size(50.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                LoginForm(
                    email = email,
                    password = password,
                    error = error,
                    onEmailChange = {
                        email = it
                        error = null
                    },
                    onPasswordChange = {
                        password = it
                        error = null
                    },
                    onLogin = {
                        error = validateLogin(email, password)

                        if (error == null) {
                            onLoggedIn()
                        }
                    },
                    onRegister = onRegister,
                    onBack = onBack
                )
            }
        }

    }
}

// Formulario completo de inicio de sesión
@Composable
private fun LoginForm(
    email: String,
    password: String,
    error: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Cream, RoundedCornerShape(20.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar sesión",
            fontSize = 24.sp,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        EmailField(
            email = email,
            onEmailChange = onEmailChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        PasswordField(
            password = password,
            onPasswordChange = onPasswordChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Muestra el error si los datos no son válidos
        error?.let {
            Text(
                text = it,
                color = Color.Red,
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        LoginButton(onClick = onLogin)

        Spacer(modifier = Modifier.height(8.dp))

        LoginNavigationButtons(
            onRegister = onRegister,
            onBack = onBack
        )
    }
}

// Campo para introducir el correo electrónico
@Composable
private fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Correo electrónico *") },
        placeholder = { Text("Introduce tu correo") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

// Campo para introducir la contraseña
@Composable
private fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña *") },
        placeholder = { Text("Introduce tu contraseña") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}

// Comprueba que el correo y la contraseña no estén vacíos
// y que el correo tenga un formato válido
private fun validateLogin(
    email: String,
    password: String
): String? {
    if (email.isBlank() || password.isBlank()) {
        return "Rellena todos los campos"
    }

    if (!android.util.Patterns.EMAIL_ADDRESS
            .matcher(email.trim())
            .matches()
    ) {
        return "Introduce un correo electrónico válido"
    }

    return null
}

// Botón para iniciar sesión
@Composable
private fun LoginButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGreen
        )
    ) {
        Text(
            text = "Iniciar sesión",
            color = Cream
        )
    }
}

// Botones para ir al registro
@Composable
private fun LoginNavigationButtons(
    onRegister: () -> Unit,
    onBack: () -> Unit
) {
    TextButton(onClick = onRegister) {
        Text(
            text = "¿No tienes cuenta? Regístrate",
            color = Green
        )
    }
}
