package com.aureapubishing.teaApp.ui.screens.infusions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aureapubishing.teaApp.R

@Composable
fun InfusionsScreen(onNext: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "Listado de infusiones",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.tertiary
            )

            InfusiosSearchBar()

            InfusionsFilterBar()
        }

        Button(
            onClick = { },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
                .size(56.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.add),
                contentDescription = "Añadir té",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}

@Composable
private fun InfusiosSearchBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Busca por nombre",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.outline
            )

            Image(
                painter = painterResource(R.drawable.search),
                contentDescription = "Buscar",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
private fun InfusionsFilterBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(0.82f),
        shape = RoundedCornerShape(50.dp),
        color = MaterialTheme.colorScheme.outline
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Filtros",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            TextButton(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Ordenados",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
