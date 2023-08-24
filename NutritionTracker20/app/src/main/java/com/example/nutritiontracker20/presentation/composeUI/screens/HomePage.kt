package com.example.nutritiontracker20.presentation.composeUI.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.data.models.states.CategoriesState
import com.example.nutritiontracker20.presentation.composeUI.elements.*
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.utils.*

@Composable
fun HomePage(mealViewModel: MealContract.ViewModel, planViewModel: PlanContract.ViewModel, navController: NavController) { //DI mealViewModel u pocetni ekran, HomePage nam je kao pocetni ekran
    val listItems = listOf(CATEGORY, AREA, INGREDIENT)
    val topAppBarSelectedItem = rememberSaveable { mutableStateOf(listItems[0]) }
    val searchFlag = rememberSaveable { mutableStateOf(false) }

    val categoriesState = mealViewModel.categoriesState.observeAsState(CategoriesState.Loading)
    val areasState = mealViewModel.areasState.observeAsState(Resource.Loading())
    val ingredientsState = mealViewModel.ingredientsState.observeAsState(Resource.Loading())

    val mealsState = mealViewModel.mealsState.observeAsState(Resource.Loading())
    val context = LocalContext.current
    var sortBy by remember { mutableStateOf(true) } // ako je true onda A-Z, ako je false onda Z-A
    Column {
        TopAppBar {
            MyDropDownMenu(
                listItems = listItems,
                modifier = Modifier.fillMaxWidth(),
                selectedOption = topAppBarSelectedItem as MutableState<Any>
            ) { selectedOption ->
                myLambdaFunForDropDownMenu(
                    selectedOption as String,
                    listItems,
                    mealViewModel
                ) // ovo je sve samo da ne bismo prosledjivali mealViewModel
                searchFlag.value = false
            }
        }
        SearchBar(onSearch = {mealViewModel.searchMeal(it)
            searchFlag.value = true})
        Button (onClick = {
            sortBy = !sortBy
        }) { Text (text = "sort: ${if (sortBy) "A-Z" else "Z-A"}") }

        LazyColumn(modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, // End
            verticalArrangement = Arrangement.Center)
        {
            if (searchFlag.value) {
                //JELA
                item {
                    when (mealsState.value) {
                        is Resource.Loading -> { CircularProgressIndicator() }
                        is Resource.Error -> {
                            Toast.makeText(
                                LocalContext.current,
                                (mealsState.value as Resource.Error).error.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            searchFlag.value = false
                        }
                        is Resource.Success -> {
                            if((mealsState.value as Resource.Success).data.isNotEmpty()) {
                                var mealsList = (mealsState.value as Resource.Success).data
                                if(!sortBy) mealsList = mealsList.sortedByDescending { meal -> meal.strMeal }
                                for (meal in mealsList) {
                                    MealListView(
                                        navController = navController,
                                        meal = meal,
                                        onClick = {
                                            if(CREATE_PLAN_MODE) {
                                                // TODO
                                                //  planViewModel.mapa
                                                //  u kom trenutku treba pozvati: CREATE_PLAN_MODE = false? ovde?
//                                                Toast.makeText(context, "CREATE_PLAN_MODE activated - HomePage", Toast.LENGTH_SHORT).show()
                                                planViewModel.setMeal(meal)
                                                CREATE_PLAN_MODE = false
                                                navController.navigate(CREATE_PLAN_SCREEN)
                                            } else {
                                                mealViewModel.getMealById(meal.idMeal)
                                                navController.navigate(MEAL_DETAIL_PAGE)
                                            }
                                        } //odmah prelazimo na detaljan prikaz jela
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                item {
                    when (topAppBarSelectedItem.value) {
                        CATEGORY -> {
                            when (categoriesState.value) {
                                is CategoriesState.Loading -> {
                                    CircularProgressIndicator()
                                }
                                is CategoriesState.Error -> {
                                    Toast.makeText(
                                        LocalContext.current,
                                        (categoriesState.value as CategoriesState.Error).message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                is CategoriesState.Success -> {
                                    var categoriesList = (categoriesState.value as CategoriesState.Success).categories
                                    if(!sortBy) categoriesList = categoriesList.sortedByDescending { cat -> cat.strCategory }
                                    for (category in categoriesList) {
                                        KategorijaListView(
                                            navController = navController,
                                            category = category,
                                            onClick = { mealViewModel.setKategorija(category) }
                                        )
                                    }
                                }
                                else -> {
                                    Toast.makeText(
                                        LocalContext.current,
                                        "Error: ${categoriesState.value}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        AREA -> {
                            when (areasState.value) {
                                is Resource.Loading -> {
                                    CircularProgressIndicator()
                                }
                                is Resource.Error -> {
                                    Toast.makeText(
                                        LocalContext.current,
                                        (areasState.value as Resource.Error).error.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                is Resource.Success -> {
                                    var areasList = (areasState.value as Resource.Success).data
                                    if(!sortBy) areasList = areasList.sortedByDescending { area -> area }
                                    for (area in areasList) {
                                        AreaListView(
                                            navController = navController,
                                            area = area,
                                            onClick = { mealViewModel.setArea(area) }
                                        )
                                    }
                                }
                            }
                        }
                        INGREDIENT -> {
                            when (ingredientsState.value) {
                                is Resource.Loading -> {
                                    CircularProgressIndicator()
                                }
                                is Resource.Error -> {
                                    Toast.makeText(
                                        LocalContext.current,
                                        (ingredientsState.value as Resource.Error).error.message,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                is Resource.Success -> {
                                    var ingredientList = (ingredientsState.value as Resource.Success).data
                                    if(!sortBy) ingredientList = ingredientList.sortedByDescending { ing -> ing.strIngredient }
                                    for (ingredient in ingredientList) {
                                        JIngredientListView(
                                            navController = navController,
                                            ingredient = ingredient,
                                            onClick = { mealViewModel.setIngredient(ingredient) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}

private fun myLambdaFunForDropDownMenu(selectedOption: String, listItems: List<String> = listOf("Kategorija", "Oblast", "Sastojci"), mealViewModel: MealContract.ViewModel) {
    when (selectedOption) {
        listItems[0] -> {
            mealViewModel.getCategories()
        }
        listItems[1] -> {
            mealViewModel.getAreas()
        }
        listItems[2] -> {
            mealViewModel.getIngredients()
        }
        else -> {/*nesto nesto */}
    }
}
