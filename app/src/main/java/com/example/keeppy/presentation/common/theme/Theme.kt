package com.example.keeppy.presentation.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = White,
    primaryVariant = Gray,
    secondary = White,
    background = White,

    )

private val DarkColorPalette = darkColors(
    primary = Black200,
    primaryVariant = Black500,
    secondary = White,
    background = Black200,

    onPrimary = White,
    onSecondary = Black200,
    onBackground = White,
)

@Composable
fun KEEPPYTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}