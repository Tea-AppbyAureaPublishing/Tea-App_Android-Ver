package com.aureapubishing.teaApp.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.components.AppBackground

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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Título
            Text(
                text = "Bienvenido a Tea-App",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.favicon),
                contentDescription = "Logo de Tea-App",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Formulario
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
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
            // Secondary100 (color para tarjetas y superficies)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.headlineMedium, // Igual que "Crear cuenta" en RegisterScreen
            color = MaterialTheme.colorScheme.onSurface // Primary500 (texto sobre superficie)
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
                style = MaterialTheme.typography.bodySmall, // Estilo del tema para textos de error/ayuda
                color = MaterialTheme.colorScheme.error // Color de error del tema
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
            containerColor = MaterialTheme.colorScheme.primary, // Secondary300 (acción principal)
            contentColor = MaterialTheme.colorScheme.onPrimary // Primary500 (texto sobre primario)
        )
    ) {
        Text(text = "Iniciar sesión")
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
            color = MaterialTheme.colorScheme.secondary // Primary400 (identidad verde secundaria)
        )
    }
}
