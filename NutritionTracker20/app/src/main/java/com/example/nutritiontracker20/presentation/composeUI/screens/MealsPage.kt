package com.example.nutritiontracker20.presentation.composeUI.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.presentation.composeUI.elements.MealListView
import com.example.nutritiontracker20.presentation.composeUI.elements.SearchBar
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.AREA
import com.example.nutritiontracker20.utils.CATEGORY
import com.example.nutritiontracker20.utils.INGREDIENT

@Composable
fun MealsPage(mealViewModel: MealContract.ViewModel, navController: NavController) {
    var counterDefault by remember { mutableStateOf(5) }

    val topAppBarState = mealViewModel.chosenTopAppBar.observeAsState()
    val chosenCategory = mealViewModel.chosenCategory.observeAsState(Resource.Loading())
    val chosenArea = mealViewModel.chosenArea.observeAsState(Resource.Loading())
    val chosenIngredient = mealViewModel.chosenIngredient.observeAsState(Resource.Loading())
    val mealsState = mealViewModel.mealsState.observeAsState(Resource.Loading())

    var txt by remember { mutableStateOf("") }
    var counter by remember { mutableStateOf(0) } //counter koji prikazuje odredjeni broj jela po stranici
    var flagUpperArrow by remember { mutableStateOf(counter > 0) } // ^ strelica na gore, za ranija jela
    var flagDownArrow by remember { mutableStateOf(true) } // v strelica na dole, za kasnija jela

    when (topAppBarState.value) {
        CATEGORY -> {
            when (chosenCategory.value) {
                is Resource.Loading -> { CircularProgressIndicator() }
                is Resource.Error -> {
                    Toast.makeText(LocalContext.current, (chosenCategory.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    mealViewModel.filterMealsByCategory((chosenCategory.value as Resource.Success).data.strCategory)
                    txt = (chosenCategory.value as Resource.Success).data.strCategory
                }
            }
        }
        AREA -> {
            when (chosenArea.value) {
                is Resource.Loading -> { CircularProgressIndicator() }
                is Resource.Error -> {
                    Toast.makeText(LocalContext.current, (chosenArea.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    mealViewModel.filterMealsByArea((chosenArea.value as Resource.Success).data)
                    txt = (chosenArea.value as Resource.Success).data
                }
            }
        }
        INGREDIENT -> {
            when (chosenIngredient.value) {
                is Resource.Loading -> { CircularProgressIndicator() }
                is Resource.Error -> {
                    Toast.makeText(LocalContext.current, (chosenIngredient.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    mealViewModel.filterMealsByIngredient((chosenIngredient.value as Resource.Success).data.strIngredient!!)
                    txt = (chosenIngredient.value as Resource.Success).data.strIngredient.toString()
                }
            }
        }
    }

    Column() {
        // naziv: Kategorije, Oblasti, Sastojka
        TopAppBar {
            Text (text = txt)
        }
        SearchBar(onSearch = {mealViewModel.searchMeal(it)}) //TODO ovo treba izmeniti i u HOME PAGE
        LazyColumn(modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, // End
            verticalArrangement = Arrangement.Center)
        {
            item {
                //za menjanje paginacije
                Row(modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically) {
                    var newCounterDef by remember { mutableStateOf(counterDefault.toString()) }
                    Text(modifier = Modifier.weight(1f).padding(horizontal = 56.dp),
                        text = "$counter - ${counter+counterDefault}")
                    TextField(
                        value = newCounterDef, //counterDefault.toString(),
                        onValueChange = { newValue ->
                            val numericValue = newValue.filter { it.isDigit() }
                            newCounterDef = numericValue
                        },
                        isError = newCounterDef == "" || newCounterDef.toInt() == 0,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(0.5f)
                    )
                    Button(modifier = Modifier.weight(0.5f),
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
            }
            item {
                Button(onClick = { counter -= counterDefault }, enabled = flagUpperArrow) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "^")
                }
            }
            item {
                when (mealsState.value) {
                    is Resource.Loading -> { CircularProgressIndicator() }
                    is Resource.Success -> {
                        flagUpperArrow = counter > 0
                        flagDownArrow = (counter+counterDefault) < (mealsState.value as Resource.Success).data.size //valjda ce moci
                        val mealsList = (mealsState.value as Resource.Success).data
//                        Log.d("SIZE", "size: ${mealsList.size}")
                        var i = counter
                        while (i < mealsList.size && i < counter+counterDefault) {
                            val meal = mealsList[i]
                            MealListView(navController = navController, meal = meal) {
                                mealViewModel.getMealById(meal.idMeal)
                            }
                            i++
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(LocalContext.current, (mealsState.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            item {
                Button(onClick = { counter += counterDefault }, enabled = flagDownArrow) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "v")
                }
            }
        }
    }
}