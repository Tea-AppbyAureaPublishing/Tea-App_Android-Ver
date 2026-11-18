
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
import androidx.compose.ui.unit.sp
import com.aureapubishing.teaApp.R
import com.aureapubishing.teaApp.ui.components.AppBackground

private val Cream = Color(0xFFFFF8E1)
private val DarkGreen = Color(0xFF2A3225)
private val Green = Color(0xFF485C43)

@Composable
fun EditProfileScreen(
    onWelcomeClick: () -> Unit,
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
                    .background(Cream, RoundedCornerShape(20.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage()

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Nombre de usuario",
                    fontSize = 15.sp,
                    color = DarkGreen
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
                        color = Color.Red,
                        fontSize = 12.sp
                    )
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

                Spacer(modifier = Modifier.height(12.dp))

                ProfileActions(
                    onLogout = onWelcomeClick,
                    onDelete = {
                        showDeleteDialog = true
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
            .background(Color(0xFFB7C9B8), CircleShape),
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
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkGreen
        )
    ) {
        Text(
            text = "Confirmar",
            color = Cream
        )
    }
}

// Botones de acciones del perfil
@Composable
private fun ProfileActions(
    onLogout: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onLogout,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Cream
            )
        ) {
            Text(
                text = "Cerrar sesión",
                color = Color.Red,
                fontSize = 11.sp
            )
        }

        Button(
            onClick = onDelete,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Cream
            )
        ) {
            Text(
                text = "Eliminar cuenta",
                color = Color.Red,
                fontSize = 11.sp
            )
        }
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
                color = DarkGreen
            )
        },
        text = {
            Column {
                Text(
                    text = "¿Estás seguro de que quieres eliminar tu cuenta? " +
                            "Esta acción será irreversible."
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Para continuar, escribe la palabra ELIMINAR:")

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmation,
                    onValueChange = {
                        confirmation = it
                        error = null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text("ELIMINAR") }
                )

                error?.let {
                    Spacer(modifier = Modifier.height(5.dp))

                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp
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
                    containerColor = Color.Red
                )
            ) {
                Text(
                    text = "Eliminar definitivamente",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancelar",
                    color = Green
                )
            }
        },
        containerColor = Cream
    )
}
