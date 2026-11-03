package com.aureapubishing.teaApp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary400,
    onPrimary = Secondary100,

    secondary = Primary200,
    onSecondary = Primary500,

    tertiary = Secondary400,
    onTertiary = Primary500,

    background = Secondary100,
    onBackground = Primary500,

    surface = Secondary100,
    onSurface = Primary500,

    surfaceVariant = Primary100,
    onSurfaceVariant = Primary500
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary200,
    onPrimary = Primary500,

    secondary = Secondary200,
    onSecondary = Primary500,

    tertiary = Secondary400,
    onTertiary = Primary500,

    background = Primary500,
    onBackground = Secondary100,

    surface = Primary400,
    onSurface = Secondary100,

    surfaceVariant = Primary300,
    onSurfaceVariant = Secondary100
)

@Composable
fun TeaAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}