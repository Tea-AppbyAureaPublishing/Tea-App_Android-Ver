package com.aureapublishing.teaApp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val TeaAppColorScheme = lightColorScheme(

    // Primary: main actions and buttons
    primary = Secondary300,
    onPrimary = Primary500,
    primaryContainer = Secondary200,
    onPrimaryContainer = Primary500,

    // Secondary: green identity
    secondary = Primary400,
    onSecondary = Secondary100,
    secondaryContainer = Primary100,
    onSecondaryContainer = Primary500,

    // Tertiary: decorative accents
    tertiary = Secondary200,
    onTertiary = Primary500,
    tertiaryContainer = Secondary100,
    onTertiaryContainer = Primary500,

    // Background
    background = Primary100,
    onBackground = Primary500,

    // Surfaces: cards and content
    surface = Secondary100,
    onSurface = Primary500,

    // Borders
    outline = Primary200,
    outlineVariant = Primary100,

    // Inverse colors
    inversePrimary = Primary200,
    inverseSurface = Primary500,
    inverseOnSurface = Secondary100,

    // Scrim
    scrim = Primary500
)

@Composable
fun TeaAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TeaAppColorScheme,
        content = content
    )
}