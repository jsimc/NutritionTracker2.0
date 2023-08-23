package com.example.nutritiontracker20.presentation.composeUI.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
//TODO provera i mealViewModel.filterByKcal
@Composable
fun KcalFilterRow(modifier: Modifier, kcalBorder: Int = 200, fromKcal: Int = 0, toKcal: Int = 1000) {
    val listItems = listOf("less then", "more then", "in between")
    val selectedOption by remember { mutableStateOf(listItems[0]) }

    var fromTextField by remember { mutableStateOf("") }
    var toTextField by remember { mutableStateOf("") }
    var valueTextField by remember { mutableStateOf("") }

    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {
        MyDropDownMenu(listItems = listItems, modifier = Modifier.fillMaxSize(), onClick = {})
        if(selectedOption == listItems[2]) {
            TextField(
                value = fromTextField,
                onValueChange = {
                    fromTextField = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
            TextField(
                value = toTextField,
                onValueChange = {toTextField = it},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        } else {
            TextField(
                value = valueTextField,
                onValueChange = {valueTextField=it},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
        }
        Button(onClick = {
            //treba mi mealViewModel.filterByKcal()
            if(selectedOption == listItems[2]) { // in between
            } else {
                //less or greater than
            }
        }) {
            Text(text = "Filter")
        }
    }
}