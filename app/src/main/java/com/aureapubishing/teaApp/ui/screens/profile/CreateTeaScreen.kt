
package com.aureapubishing.teaApp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import com.aureapublishing.teaApp.ui.theme.*

@Composable
fun CreateTeaScreen(
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var benefits by remember { mutableStateOf("") }
    var tastingNotes by remember { mutableStateOf("") }
    var preparation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Añade tu propio té",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Secondary200
        )

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Primary500.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp)
        ) {
            TeaFormField(
                label = "Nombre",
                value = name,
                onValueChange = { name = it },
                placeholder = "Introduce el nombre del té"
            )

            TeaFormField(
                label = "Descripción",
                value = description,
                onValueChange = { description = it },
                placeholder = "Introduce la descripción del té"
            )

            TeaFormField(
                label = "Beneficios",
                value = benefits,
                onValueChange = { benefits = it },
                placeholder = "Sepáralos por ,"
            )

            TeaFormField(
                label = "Notas de Cata",
                value = tastingNotes,
                onValueChange = { tastingNotes = it },
                placeholder = "Sepáralas por ,"
            )

            TeaFormField(
                label = "Preparación",
                value = preparation,
                onValueChange = { preparation = it },
                placeholder = "Opcionalmente añade preparación",
                multiline = true
            )

            Spacer(Modifier.height(18.dp))

            Button(
                onClick = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Secondary300
                )
            ) {
                Text(
                    text = "Añade tu té",
                    color = Primary500,
                    fontSize = 17.sp
                )
            }
        }

        Spacer(Modifier.height(20.dp))
    }
}

@Composable
fun TeaFormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    multiline: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 17.sp,
            color = Secondary300,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(4.dp))

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = !multiline,
            textStyle = TextStyle(
                color = Primary500,
                fontSize = 14.sp
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Secondary100,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .padding(
                            horizontal = 16.dp,
                            vertical = 10.dp
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Primary100,
                            fontSize = 13.sp
                        )
                    }

                    innerTextField()
                }
            }
        )

        Spacer(Modifier.height(8.dp))
    }
}
