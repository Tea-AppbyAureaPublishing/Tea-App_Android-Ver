
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.components.AppBackground

// Pantalla principal de registro
@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onLogin: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var error by remember { mutableStateOf<String?>(null) }
    var message by remember { mutableStateOf<String?>(null) }

    AppBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Bienvenido a Tea-App",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                painter = painterResource(id = R.drawable.favicon),
                contentDescription = "Logo de Tea-App",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                RegisterForm(
                    email = email,
                    password = password,
                    confirmPassword = confirmPassword,
                    error = error,
                    message = message,
                    onEmailChange = {
                        email = it
                        error = null
                        message = null
                    },
                    onPasswordChange = {
                        password = it
                        error = null
                        message = null
                    },
                    onConfirmPasswordChange = {
                        confirmPassword = it
                        error = null
                        message = null
                    },
                    onRegister = {
                        error = validateRegister(
                            name,
                            email,
                            password,
                            confirmPassword
                        )

                        if (error == null) {
                            // FUTURA CONEXIÓN CON LA BBDD
                            onLogin()
                        }
                    },
                    onLogin = onLogin,
                    onBack = onBack
                )
            }
        }
    }
}

// Formulario completo de registro
@Composable
private fun RegisterForm(
    email: String,
    password: String,
    confirmPassword: String,
    error: String?,
    message: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onRegister: () -> Unit,
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crear cuenta",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(12.dp))

        EmailField(email, onEmailChange)

        Spacer(modifier = Modifier.height(12.dp))

        PasswordField(
            password = password,
            label = "Contraseña",
            onPasswordChange = onPasswordChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        PasswordField(
            password = confirmPassword,
            label = "Confirmar contraseña",
            onPasswordChange = onConfirmPasswordChange
        )

        Spacer(modifier = Modifier.height(10.dp))

        FormMessages(
            error = error,
            message = message
        )

        Spacer(modifier = Modifier.height(10.dp))

        RegisterButton(onClick = onRegister)

        RegisterNavigationButtons(
            onLogin = onLogin,
            onBack = onBack
        )
    }
}

// Campo para introducir el correo
@Composable
private fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Correo electrónico") },
        placeholder = { Text("Introduce tu correo") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

// Campo para introducir una contraseña
@Composable
private fun PasswordField(
    password: String,
    label: String,
    onPasswordChange: (String) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}

// Comprueba que los datos del registro sean correctos
private fun validateRegister(
    name: String,
    email: String,
    password: String,
    confirmPassword: String
): String? {
    if (
        email.isBlank() ||
        password.isBlank() ||
        confirmPassword.isBlank()
    ) {
        return "Completa todos los campos"
    }

    if (!android.util.Patterns.EMAIL_ADDRESS
            .matcher(email.trim())
            .matches()
    ) {
        return "Introduce un correo electrónico válido"
    }

    if (password != confirmPassword) {
        return "Las contraseñas no coinciden"
    }

    return null
}

// Muestra los mensajes de error o información
@Composable
private fun FormMessages(
    error: String?,
    message: String?
) {
    error?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.error
        )
    }

    message?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

// Botón para crear la cuenta
@Composable
private fun RegisterButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text("Crear cuenta")
    }
}

// Botones para navegar entre registro e inicio de sesión
@Composable
private fun RegisterNavigationButtons(
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    TextButton(
        onClick = onLogin,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "¿Ya tienes cuenta? Inicia sesión",
            color = MaterialTheme.colorScheme.secondary
        )
    }
}
