package com.example.nutritiontracker20.presentation.composeUI.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = TamnaGlavnaBoja,
    primaryVariant = JednaLjubicastaBojaRedaRadi,
    secondary = GlavnaBoja,
    background = TamnaDrugaGlavnaBoja,
    onBackground = BelicastaBoja,
    surface = JednaTamnijaLjubicastaBojaRedaRadi,
    onSurface = BelicastaBoja,
    onPrimary = BelicastaBoja
)

private val LightColorPalette = lightColors(
    primary = GlavnaBoja,
    primaryVariant = JednaLjubicastaBojaRedaRadi,
    secondary = DrugaGlavnaBoja,
    background = BelicastaBoja,
    onBackground = TamnaGlavnaBoja,
    surface = JednaLjubicastaBojaRedaRadi
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun NasaTema(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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