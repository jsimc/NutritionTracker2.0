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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.data.models.states.CategoriesState
import com.example.nutritiontracker20.presentation.composeUI.elements.FavMealAreaListView
import com.example.nutritiontracker20.presentation.composeUI.elements.FavMealCategoryListView
import com.example.nutritiontracker20.presentation.composeUI.elements.FavMealListView
import com.example.nutritiontracker20.presentation.composeUI.elements.MyDropDownMenu
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.AREA
import com.example.nutritiontracker20.utils.CATEGORY
import com.example.nutritiontracker20.utils.MEAL

@Composable
fun FavoritesScreen(mealViewModel: MealContract.ViewModel) {
    val listItems = listOf(MEAL, CATEGORY, AREA)
    val topAppBarSelectedItem = rememberSaveable { mutableStateOf(listItems[0]) }

    var counterDefault by rememberSaveable { mutableStateOf(5) }
    var counter by remember { mutableStateOf(0) }
    var flagUpperArrow by remember { mutableStateOf(counter > 0) } // ^ strelica na gore, za ranija jela
    var flagDownArrow by remember { mutableStateOf(true) } // v strelica na dole, za kasnija jela

    val favoriteMealsState = mealViewModel.favoriteMealsState.observeAsState(Resource.Loading())

    DisposableEffect(Unit) {
        // This block of code will run when the composable is first composed
        // Perform any actions or initialization here
        mealViewModel.getAllCountDescByName()

        // Dispose of any resources if needed when the composable leaves the composition
        onDispose {
            // This block of code will run when the composable is removed
            // Perform cleanup or resource release here
        }
    }


    Column {
        // za dropDownMenu kao u HomePage
        TopAppBar {
            MyDropDownMenu(
                listItems = listItems,
                modifier = Modifier.fillMaxWidth(),
                selectedOption = topAppBarSelectedItem as MutableState<Any>
            ) { selectedOption ->
                // todo ako pise meals samo izlista sva jela iz baze sortirana po broju pojavljivanja, to napisi neki query
                when(selectedOption) {
                    listItems[0] -> {
                        mealViewModel.getAllCountDescByName()
                    }
                    listItems[1] -> {
                        mealViewModel.getAllCountDescByCategory()
                    }
                    listItems[2] -> {
                        mealViewModel.getAllCountDescByArea()
                    }
                    else -> {/* nesto nesto */}
                }
            }
        }
        // PAGINACIJA, kao u mealsPage
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
        //za prikaz sacuvane hrane iz Baze
        LazyColumn {
            // STRELICA GORE
            item {
                Button(onClick = { counter -= counterDefault }, enabled = flagUpperArrow) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "^")
                }
            }
            // FAV meals
            item {
                when(favoriteMealsState.value) {
                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            LocalContext.current,
                            (favoriteMealsState.value as Resource.Error).error.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Resource.Success -> {
                        val favMealsList = (favoriteMealsState.value as Resource.Success).data
                        flagUpperArrow = counter > 0
                        flagDownArrow = (counter+counterDefault) < favMealsList.size
                        var i = counter
                        while (i < favMealsList.size && i < counter+counterDefault) {
                            val favMeal = favMealsList[i]
                            when(topAppBarSelectedItem.value) {
                                listItems[0] -> {
                                    // by name
                                    FavMealListView(
                                        meal = favMeal.savedMeal.meal,
                                        photo = favMeal.savedMeal.photo,
                                        count = favMeal.count) {}
                                }
                                listItems[1] -> {
                                    // by category
                                    FavMealCategoryListView(
                                        category = favMeal.savedMeal.category,
                                        count = favMeal.count
                                    ) {}
                                }
                                listItems[2] -> {
                                    // by area
                                    FavMealAreaListView(
                                        area = favMeal.savedMeal.region,
                                        count = favMeal.count) {}
                                }
                                else -> {
                                    FavMealListView(
                                        meal = favMeal.savedMeal.meal,
                                        photo = favMeal.savedMeal.photo,
                                        count = favMeal.count) {}
                                }
                            }
                            i++
                        }
                    }
                }
            }
            // STRELICA DOLE
            item {
                Button(onClick = { counter += counterDefault }, enabled = flagDownArrow) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "v")
                }
            }
        }
    }
}