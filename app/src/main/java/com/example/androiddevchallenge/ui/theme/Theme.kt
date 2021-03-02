package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = primary,
    primaryVariant = primary,
    onPrimary = Color.Black,
    secondary = Black,
    secondaryVariant = Black,
    onSecondary = Color.Black,
    error = Black
)

private val DarkThemeColors = darkColors(
    primary = primary,
    primaryVariant = primary,
    onPrimary = Color.Black,
    secondary = primary,
    onSecondary = Color.White,
    error = primary
)

@Composable
fun PetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = petTypography,
        shapes = petShapes,
        content = content
    )
}
