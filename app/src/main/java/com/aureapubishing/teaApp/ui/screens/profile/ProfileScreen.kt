
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aureapubishing.teaApp.R

@Composable
fun ProfileScreen(
    onNext: () -> Unit,
    onCreateTeaClick: () -> Unit
) {
    var selectedSection by remember { mutableStateOf("Tés propios") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileContent()

        ProfileOption(
            selectedSection = selectedSection,
            onSectionSelected = { selectedSection = it }
        )

        when (selectedSection) {
            "Tés propios" -> MyTeasContent(
                onAddTeaClick = onCreateTeaClick
            )

            "Tés favoritos" -> {
                EmptySectionContent("Todavía no tienes tés favoritos")
            }

            "Pedidos" -> {
                EmptySectionContent("Todavía no tienes pedidos")
            }
        }
    }
}

@Composable
fun ProfileContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Mi perfil",
            fontSize = 30.sp,
            color = Color(0xFFF2C438)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ProfileUserCard()
    }
}

@Composable
fun ProfileUserCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFF8E1),
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
                Text(
                    text = "Nombre de usuario",
                    fontSize = 20.sp,
                    color = Color(0xFF2A3225)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "correo@ejemplo.com",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2A3225)
                    )
                ) {
                    Text(
                        text = "Editar perfil",
                        color = Color(0xFFFFF8E1)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileOption(
    selectedSection: String,
    onSectionSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("Tés favoritos", "Tés propios", "Pedidos").forEach { section ->
                Button(
                    onClick = { onSectionSelected(section) },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(
                        horizontal = 4.dp,
                        vertical = 8.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedSection == section) {
                            Color(0xFFE4AC26)
                        } else {
                            Color(0xFFFFF8E1)
                        }
                    )
                ) {
                    Text(
                        text = section,
                        color = Color(0xFF2A3225),
                        fontSize = 11.sp,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun MyTeasContent(
    onAddTeaClick: () -> Unit
) {
    val teas = listOf(
        "Té verde",
        "Té de jazmín",
        "Té chai"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Mis tés",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFE0BB53)
        )

        Spacer(modifier = Modifier.height(15.dp))

        teas.forEach { tea ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .background(
                        color = Color(0xFFFFF8E1),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tea,
                    fontSize = 17.sp,
                    color = Color(0xFF2A3225)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = onAddTeaClick,
                modifier = Modifier.size(55.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE4AC26)
                )
            ) {
                Text(
                    text = "+",
                    fontSize = 30.sp,
                    color = Color(0xFF2A3225)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun EmptySectionContent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color(0xFF2A3225),
            fontSize = 16.sp
        )
    }
}
