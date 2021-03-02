package com.example.androiddevchallenge.ui

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.theme.PetTheme

@Composable
internal fun ThemedPreview(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    PetTheme(darkTheme = darkTheme) {
        Surface {
            content()
        }
    }
}
