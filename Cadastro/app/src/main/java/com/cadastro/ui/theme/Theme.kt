package com.cadastro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color.Magenta,
    secondary = Color.Blue,
    background = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Magenta,
    secondary = Color.Blue,
    background = Color.White
)

@Composable
fun CadastroTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
