package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.nutritiontracker20.R
import com.example.nutritiontracker20.utils.MAIN_GRAPH
import kotlinx.coroutines.delay

@Composable
fun MySplashScreen(navController: NavController) {
    val splashScreenShown = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        splashScreenShown.value = true
        delay(1000L) // Delay for 3 seconds
        navController.popBackStack()
        navController.navigate(MAIN_GRAPH)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Show your splash screen content here
        Column() {
            Image(
                //TODO Natin logo SVG
                painter = painterResource(R.drawable.baza_sharp_108x108),
                contentDescription = "Image"
            )
            Text (text = "splash screen")
        }
    }
}