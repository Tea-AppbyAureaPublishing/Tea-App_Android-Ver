package com.aureapubishing.teaApp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.components.AppBackground

@Composable
fun EditProfileScreen(
    onLogoutClick: () -> Unit,
    onDeleteAccount: () -> Unit,
    onConfirm: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    var error by remember { mutableStateOf<String?>(null) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    AppBackground {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.88f)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage()

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Nombre de usuario",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(10.dp))

                ProfileFields(
                    email = email,
                    password = password,
                    repeatPassword = repeatPassword,
                    onEmailChange = {
                        email = it
                        error = null
                    },
                    onPasswordChange = {
                        password = it
                        error = null
                    },
                    onRepeatPasswordChange = {
                        repeatPassword = it
                        error = null
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                error?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                ConfirmChangesButton(
                    onClick = {
                        error = validateProfileData(
                            email,
                            password,
                            repeatPassword
                        )

                        if (error == null) {
                            onConfirm()
                        }
                    }
                )

            }
        }
    }

    if (showDeleteDialog) {
        DeleteAccountDialog(
            onDismiss = {
                showDeleteDialog = false
            },
            onDelete = {
                showDeleteDialog = false

                // FUTURA CONEXIÓN CON LA BBDD:
                // Aquí llamaremos a la función que eliminará
                // la cuenta del usuario en el servidor.

                onDeleteAccount()
            }
        )
    }
}

// Imagen del perfil
@Composable
private fun ProfileImage() {
    Box(
        modifier = Modifier
            .size(85.dp)
            .background(MaterialTheme.colorScheme.outline, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.favicon),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

// Campos del perfil
@Composable
private fun ProfileFields(
    email: String,
    password: String,
    repeatPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Correo electrónico *") },
        placeholder = { Text("Introduce tu correo") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(5.dp))

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Nueva contraseña *") },
        placeholder = { Text("Introduce tu contraseña") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )

    Spacer(modifier = Modifier.height(5.dp))

    OutlinedTextField(
        value = repeatPassword,
        onValueChange = onRepeatPasswordChange,
        label = { Text("Repetir contraseña *") },
        placeholder = { Text("Repite tu contraseña") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = PasswordVisualTransformation()
    )
}

// Validación de los datos
private fun validateProfileData(
    email: String,
    password: String,
    repeatPassword: String
): String? {
    if (email.isBlank() || password.isBlank() || repeatPassword.isBlank()) {
        return "Rellena todos los campos"
    }

    if (!android.util.Patterns.EMAIL_ADDRESS
            .matcher(email.trim())
            .matches()
    ) {
        return "Introduce un correo electrónico válido"
    }

    if (password.any { it.code !in 0..127 }) {
        return "La contraseña solo puede contener caracteres ASCII"
    }

    if (password.length < 8) {
        return "La contraseña debe tener al menos 8 caracteres"
    }

    if (password != repeatPassword) {
        return "Las contraseñas no coinciden"
    }

    return null
}

// Botón para confirmar cambios
@Composable
private fun ConfirmChangesButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
    ) {
        Text(
            text = "Confirmar cambios",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

// Ventana de doble confirmación
@Composable
private fun DeleteAccountDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    var confirmation by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Eliminar cuenta",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Column {
                Text(
                    text = "¿Estás seguro de que quieres eliminar tu cuenta? " +
                            "Esta acción será irreversible.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Para continuar, escribe la palabra ELIMINAR:",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmation,
                    onValueChange = {
                        confirmation = it
                        error = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "ELIMINAR",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
                error?.let {
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (confirmation == "ELIMINAR") {
                        onDelete()
                    } else {
                        error = "Debes escribir exactamente ELIMINAR"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text(
                    text = "Eliminar definitivamente",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancelar",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    )
}