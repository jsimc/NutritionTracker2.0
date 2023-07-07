package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nutritiontracker20.presentation.composeUI.elements.PlanGridView

@Preview
@Composable
fun CreatePlanScreen() {
    // 7 dana u nedelji svaki kad se klikne izadje lista jela za taj dan

    Column {
        TopAppBar() {
            Text(text = "Create Plan")
        }
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var email by remember { mutableStateOf("") }
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") })
                    //Ovde mi treba jedan lazy row: <- 26.06.2023. - 2.7.2023. ->
                    // da ima sacuvano samo npr prvi datum, ali kako onda da znam kad kliknem na neko polje u planGridView
                    // koji je datum i da li je izabrano Rucak Dorucak ili Vecera?
                    PlanGridView()

                }
            }
            item {
                Button(onClick = { /*TODO posalji mejl*/ }) {
                    Text(text = "Send mail")
                }
            }
        }
    }
}