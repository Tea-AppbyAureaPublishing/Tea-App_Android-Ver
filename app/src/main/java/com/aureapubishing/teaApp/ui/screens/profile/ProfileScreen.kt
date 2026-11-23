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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R


// Pantalla principal del perfil
@Composable
fun ProfileScreen(
    onEditProfileClick: () -> Unit
) {
    // Al entrar no habrá ninguna sección seleccionada
    var selectedSection by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
    ){
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
                selectedSection = selectedSection
            )
        }
    }
}

// Decide qué contenido mostrar según la sección seleccionada
@Composable
private fun SelectedProfileSection(
    selectedSection: String?
) {
    when (selectedSection) {
        "Tés propios" -> {
            MyTeasContent()
        }

        "Tés favoritos" -> {
            EmptySectionContent("Todavía no tienes tés favoritos")
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
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary // Secondary300 (amarillo destacado del tema)
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
                color = MaterialTheme.colorScheme.surface, // Secondary100 (fondo de tarjeta)
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
                color = MaterialTheme.colorScheme.outline, // Primary200 (verde suave para el avatar)
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
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
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface // Primary500 (texto sobre superficie)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "correo@ejemplo.com",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant // Color secundario legible sobre superficie
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onEditProfileClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseSurface, // Primary500 (verde muy oscuro)
                contentColor = MaterialTheme.colorScheme.inverseOnSurface // Secondary100 (crema)
            )
        ) {
            Text(text = "Editar perfil")
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
        "Tés propios"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            sections.forEach { section ->
                ProfileSectionButton(
                    section = section,
                    isSelected = selectedSection == section,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onSectionSelected(section)
                    }
                )
            }
        }
    }
}

// Botón individual de cada sección
@Composable
private fun ProfileSectionButton(
    section: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 2.dp,
            vertical = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            // Seleccionado: Primary400 / Secondary100 | No seleccionado: Secondary100 / Primary500
            containerColor = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = section,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1
        )
    }
}

// Contenido de la sección de tés propios
@Composable
fun MyTeasContent() {
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
                color = MaterialTheme.colorScheme.surface, // Secondary100
                shape = RoundedCornerShape(16.dp)
            )
            .padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = tea,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface // Primary500
        )
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
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground // Primary500 (texto sobre fondo general)
        )
    }
}