
package com.aureapubishing.teaApp.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aureapubishing.teaApp.R

// Colores de la pantalla
private val Cream = Color(0xFFFFF8E1)
private val DarkGreen = Color(0xFF2A3225)
private val Green = Color(0xFF485C43)
private val Yellow = Color(0xFFF2C438)
private val AvatarGreen = Color(0xFFB7C9B8)
private val ButtonYellow = Color(0xFFE4AC26)

// Pantalla principal del perfil
@Composable
fun ProfileScreen(
    onNext: () -> Unit,
    onCreateTeaClick: () -> Unit,
    onEditProfileClick: () -> Unit
) {
    // Al entrar no habrá ninguna sección seleccionada
    var selectedSection by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileContent(
            onEditProfileClick = onEditProfileClick
        )

        ProfileOption(
            selectedSection = selectedSection,
            onSectionSelected = { selectedSection = it }
        )

        // Muestra el contenido de la sección seleccionada
        SelectedProfileSection(
            selectedSection = selectedSection,
            onCreateTeaClick = onCreateTeaClick
        )
    }
}

// Decide qué contenido mostrar según la sección seleccionada
@Composable
private fun SelectedProfileSection(
    selectedSection: String?,
    onCreateTeaClick: () -> Unit
) {
    when (selectedSection) {
        "Tés propios" -> {
            MyTeasContent(
                onAddTeaClick = onCreateTeaClick
            )
        }

        "Tés favoritos" -> {
            EmptySectionContent("Todavía no tienes tés favoritos")
        }

        "Pedidos" -> {
            EmptySectionContent("Todavía no tienes pedidos")
        }

        null -> {
            // No mostramos ninguna sección al entrar
        }
    }
}

// Contenido superior del perfil
@Composable
fun ProfileContent(
    onEditProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Mi perfil",
            fontSize = 30.sp,
            color = Yellow
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileUserCard(
            onEditProfileClick = onEditProfileClick
        )
    }
}

// Tarjeta con la información del usuario
@Composable
fun ProfileUserCard(
    onEditProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Cream,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            ProfileImage()

            ProfileUserInformation(
                onEditProfileClick = onEditProfileClick
            )
        }
    }
}

// Imagen circular del perfil
@Composable
private fun ProfileImage() {
    Box(
        modifier = Modifier
            .size(110.dp)
            .background(
                color = AvatarGreen,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

// Nombre, correo y botón de edición
@Composable
private fun ProfileUserInformation(
    onEditProfileClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nombre de usuario",
            fontSize = 20.sp,
            color = DarkGreen
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "correo@ejemplo.com",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onEditProfileClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = DarkGreen
            )
        ) {
            Text(
                text = "Editar perfil",
                color = Cream
            )
        }
    }
}

// Opciones para cambiar entre las secciones del perfil
@Composable
fun ProfileOption(
    selectedSection: String?,
    onSectionSelected: (String) -> Unit
) {
    val sections = listOf(
        "Tés favoritos",
        "Tés propios",
        "Pedidos"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Repartimos el ancho disponible entre los tres botones
            val buttonWidth = (maxWidth - 16.dp) / 3

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                sections.forEach { section ->
                    ProfileSectionButton(
                        section = section,
                        isSelected = selectedSection == section,
                        buttonWidth = buttonWidth,
                        onClick = {
                            onSectionSelected(section)
                        }
                    )
                }
            }
        }
    }
}

// Botón individual de cada sección
@Composable
private fun ProfileSectionButton(
    section: String,
    isSelected: Boolean,
    buttonWidth: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(buttonWidth),
        contentPadding = PaddingValues(
            horizontal = 2.dp,
            vertical = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Green else Cream,
            contentColor = if (isSelected) Cream else DarkGreen
        )
    ) {
        Text(
            text = section,
            fontSize = 11.sp,
            maxLines = 1
        )
    }
}

// Contenido de la sección de tés propios
@Composable
fun MyTeasContent(
    onAddTeaClick: () -> Unit
) {
    // Lista provisional donde guardaremos los tés del usuario
    val teas = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        if (teas.isEmpty()) {
            EmptySectionContent("Todavía no tienes tés propios")
        } else {
            teas.forEach { tea ->
                MyTeaItem(tea)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        AddTeaButton(onClick = onAddTeaClick)

        Spacer(modifier = Modifier.height(20.dp))
    }
}

// Tarjeta individual de un té propio
@Composable
private fun MyTeaItem(
    tea: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(
                color = Cream,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = tea,
            fontSize = 17.sp,
            color = DarkGreen
        )
    }
}

// Botón para añadir un nuevo té
@Composable
private fun AddTeaButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(55.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonYellow
            )
        ) {
            Text(
                text = "+",
                fontSize = 30.sp,
                color = DarkGreen
            )
        }
    }
}

// Mensaje para las secciones que todavía están vacías
@Composable
fun EmptySectionContent(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = DarkGreen,
            fontSize = 16.sp
        )
    }
}
