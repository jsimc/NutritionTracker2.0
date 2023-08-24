package com.example.nutritiontracker20.presentation.composeUI.screens

import android.util.Log
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
import androidx.navigation.NavController
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.presentation.composeUI.elements.MealListView
import com.example.nutritiontracker20.presentation.composeUI.elements.MultiComboBox
import com.example.nutritiontracker20.presentation.composeUI.elements.MyDropDownMenu
import com.example.nutritiontracker20.presentation.composeUI.elements.SearchBar
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.utils.*

@Composable
fun MealsPage(mealViewModel: MealContract.ViewModel, planViewModel: PlanContract.ViewModel, navController: NavController) {
    val context = LocalContext.current

    var counterDefault by rememberSaveable { mutableStateOf(5) }

    val topAppBarState = mealViewModel.chosenTopAppBar.observeAsState()
    val chosenCategory = mealViewModel.chosenCategory.observeAsState(Resource.Loading())
    val chosenArea = mealViewModel.chosenArea.observeAsState(Resource.Loading())
    val chosenIngredient = mealViewModel.chosenIngredient.observeAsState(Resource.Loading())
    val mealsState = mealViewModel.mealsState.observeAsState(Resource.Loading())

    var txt by remember { mutableStateOf("") }
    var counter by remember { mutableStateOf(0) } //counter koji prikazuje odredjeni broj jela po stranici
    var flagUpperArrow by remember { mutableStateOf(counter > 0) } // ^ strelica na gore, za ranija jela
    var flagDownArrow by remember { mutableStateOf(true) } // v strelica na dole, za kasnija jela
    var sortBy by remember { mutableStateOf(false) }
    val kcalFilterListItems by remember { mutableStateOf(listOf("less than", "more than", "in between")) }
    val kcalFilterSelectedOption = rememberSaveable { mutableStateOf(kcalFilterListItems[0]) }
    var fromTfValue by rememberSaveable { mutableStateOf("") }
    var toTfValue by rememberSaveable { mutableStateOf("") }
    var tfValue by rememberSaveable { mutableStateOf("") }

    when (topAppBarState.value) {
        CATEGORY -> {
            when (chosenCategory.value) {
                is Resource.Loading -> { CircularProgressIndicator() }
                is Resource.Error -> {
                    Toast.makeText(context, (chosenCategory.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context, (chosenArea.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(context, (chosenIngredient.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
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
        SearchBar(onSearch = {
            mealViewModel.searchMeal(it)
        })
        LazyColumn(modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, // End
            verticalArrangement = Arrangement.Center)
        {
            // PAGINACIJA
            item {
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
            }
            // TAGOVI
            item {
                Row (modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically) {
                    MultiComboBox(labelText = "Tags", options = TAGS, onOptionsChosen = {
                        mealViewModel.filterMealsByTags(it)
                        Log.d("MealsPage", "Tag Chooser: $it")})
                }
            }
            // TODO smisli kako treba da izgleda filtriranje po kategorijama.
            //FILTER BY CALORIES
            item {
                Row(modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    MyDropDownMenu(
                        listItems = kcalFilterListItems,
                        selectedOption = kcalFilterSelectedOption as MutableState<Any>,
                        modifier = Modifier.weight(1f)
                    ) {}
                    if(kcalFilterSelectedOption.value == "in between") {
                        TextField(
                            value = fromTfValue,
                            onValueChange = {
                                fromTfValue = it
                            },
                            modifier = Modifier.weight(.7f),
                            label = { Text(text = "from")}
                        )
                        TextField(
                            value = toTfValue,
                            onValueChange = {
                                toTfValue = it
                            },
                            modifier = Modifier.weight(.7f),
                            label = { Text(text = "to")}
                        )
                    } else {
                        TextField(
                            value = tfValue,
                            onValueChange = {
                                tfValue = it
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Button(
                        modifier = Modifier.weight(.7f),
                        onClick = {
                            // filter by kcal
                            // TODO nije mi jasno da li se filtriranje po kalorijama radi
                            //  nad vec prikazanim jelima ili uz search?
                            //  mealVIewModel.filterByKcal(from, to) -- ako je less then onda je from = 0, to = value npr. ...
                        }
                    ) {
                        Text(text = "filter by kcal")
                    }
                }
            }
            // SORT: A-Z/Z-A
            item {
                Button (onClick = {
                    sortBy = !sortBy
                }) { Text (text = "sort: ${if (!sortBy) "A-Z" else "Z-A"}") }
            }
            // STRELICA GORE
            item {
                Button(onClick = { counter -= counterDefault }, enabled = flagUpperArrow) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "^")
                }
            }
            // LISTA JELA
            item {
                when (mealsState.value) {
                    is Resource.Loading -> { CircularProgressIndicator() }
                    is Resource.Error -> {
                        Toast.makeText(context, (mealsState.value as Resource.Error).error.message ?: "Error", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        flagUpperArrow = counter > 0
                        flagDownArrow = (counter+counterDefault) < (mealsState.value as Resource.Success).data.size //valjda ce moci
                        var mealsList = (mealsState.value as Resource.Success).data
                        if(!sortBy) mealsList = mealsList.sortedByDescending { meal -> meal.strMeal }
                        var i = counter
                        while (i < mealsList.size && i < counter+counterDefault) {
                            val meal = mealsList[i]
                            MealListView(navController = navController, meal = meal) {
                                if(CREATE_PLAN_MODE) {
                                    // TODO
                                    //  planViewModel.mapa
                                    //  u kom trenutku treba pozvati: CREATE_PLAN_MODE = false? ovde?
//                                    Toast.makeText(context, "CREATE_PLAN_MODE activated - MealsPage", Toast.LENGTH_SHORT).show()
                                    planViewModel.setMeal(meal) // --> interno vec planViewModel zna dan u nedelji i obrok u danu koji biramo
                                    CREATE_PLAN_MODE = false
                                    navController.navigate(CREATE_PLAN_SCREEN)
                                } else {
                                    mealViewModel.getMealById(meal.idMeal)
                                    navController.navigate(MEAL_DETAIL_PAGE)
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