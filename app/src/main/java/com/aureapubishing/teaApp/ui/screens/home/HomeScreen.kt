
package com.aureapubishing.teaApp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R
import androidx.compose.foundation.clickable

@Composable
fun HomeScreen(
    onNext: () -> Unit
) {
    HomeContent()
}

@Composable
fun HomeContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {

        Text(
            text = "Descubre Tu Té\nIdeal",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center
        )

        HomeFeatureCard(
            icon = R.drawable.benefits,
            title = "Beneficios Salud",
            description = "Conoce las propiedades\ny beneficios de los tés"
        )

        HomeFeatureCard(
            icon = R.drawable.tasting_note,
            title = "Notas de Cata",
            description = "Conoce los sabores y\ngustos de los tés"
        )

        Button(
            onClick = { },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .size(width = 156.dp, height = 52.dp)
        ) {
            Text(
                text = "Buscar",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun HomeFeatureCard(
    icon: Int,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(75.dp),
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colorScheme.onSecondary
                    )
                )
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Text(
                    text = "›",
                    modifier = Modifier.align(Alignment.BottomEnd),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
