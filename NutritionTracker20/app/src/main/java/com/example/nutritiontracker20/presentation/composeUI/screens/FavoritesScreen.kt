package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nutritiontracker20.utils.AREA
import com.example.nutritiontracker20.utils.CATEGORY
import com.example.nutritiontracker20.utils.MEAL

@Composable
fun FavoritesScreen() {
    val listItems = listOf(MEAL, CATEGORY, AREA)
    var counterDefault by rememberSaveable { mutableStateOf(5) }
    var counter by remember { mutableStateOf(0) }
    var flagUpperArrow by remember { mutableStateOf(counter > 0) } // ^ strelica na gore, za ranija jela
    var flagDownArrow by remember { mutableStateOf(true) } // v strelica na dole, za kasnija jela

    Column {
        // za dropDownMenu kao u HomePage
        TopAppBar {

        }
        // paginacija, kao u mealsPage
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 52.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            var newCounterDef by remember { mutableStateOf(counterDefault.toString()) }
//                    Text(modifier = Modifier
//                        .weight(1f)
//                        .padding(horizontal = 56.dp),
//                        text = "$counter - ${counter+counterDefault}")
            TextField(
                value = newCounterDef, //counterDefault.toString(),
                onValueChange = { newValue ->
                    val numericValue = newValue.filter { it.isDigit() }
                    newCounterDef = numericValue
                },
                isError = newCounterDef == "" || newCounterDef.toInt() == 0,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Button(modifier = Modifier.weight(.5f),
                onClick = {
                    if(newCounterDef != "" || newCounterDef.toInt() != 0) {
                        counterDefault = newCounterDef.toInt()
                        counter = 0
                    }
                }
            ) {
                Text(text = "Change counter")
            }
        }
        // STRELICA GORE
        Button(onClick = { counter -= counterDefault }, enabled = flagUpperArrow) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "^")
        }
        //za prikaz sacuvane hrane iz Baze
        LazyColumn() {
            // fav meals
        }
        // STRELICA DOLE
        Button(onClick = { counter += counterDefault }, enabled = flagDownArrow) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "v")
        }
    }
}