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
import com.example.nutritiontracker20.utils.AREA
import com.example.nutritiontracker20.utils.CATEGORY
import com.example.nutritiontracker20.utils.INGREDIENT

@Composable
fun HomePage(mealViewModel: MealContract.ViewModel, navController: NavController) { //DI mealViewModel u pocetni ekran, HomePage nam je kao pocetni ekran
    val listItems = listOf(CATEGORY, AREA, INGREDIENT)
    val topAppBarSelectedItem = rememberSaveable { mutableStateOf(listItems[0]) }

    val categoriesState = mealViewModel.categoriesState.observeAsState(CategoriesState.Loading)
    val areasState = mealViewModel.areasState.observeAsState(Resource.Loading())
    val ingredientsState = mealViewModel.ingredientsState.observeAsState(Resource.Loading())

    Column {
        TopAppBar {
            //TODO ovde mozemo da ubacimo npr dugme gde mozemo videti sve favorite
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
            }
        }
        SearchBar(onSearch = {mealViewModel.searchCategory(it)})
//        TextField (modifier = Modifier.fillMaxWidth(), value = "",
//                onValueChange = {mealViewModel.search(it)})
        Row (horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Button (onClick = {}) { Text (text = "filter") }
            Button (onClick = {}) { Text (text = "sort") }
        }
        LazyColumn(modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, // End
            verticalArrangement = Arrangement.Center)
        {
            item{
                if(topAppBarSelectedItem.value == CATEGORY) {
                    when (categoriesState.value) {
                        is CategoriesState.Loading -> { CircularProgressIndicator() }
                        is CategoriesState.Error -> {
                            Toast.makeText(LocalContext.current, (categoriesState.value as CategoriesState.Error).message, Toast.LENGTH_SHORT).show()
                        }
                        is CategoriesState.Success -> {
                            for (category in (categoriesState.value as CategoriesState.Success).categories) {
                                KategorijaListView(
                                    navController = navController,
                                    category = category,
                                    onClick = {mealViewModel.setKategorija(category)}
                                )
                            }
                        }
                        else -> {
                            Toast.makeText(LocalContext.current, "Error: ${categoriesState.value}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else if(topAppBarSelectedItem.value == AREA) {
                    when (areasState.value) {
                        is Resource.Loading -> { CircularProgressIndicator() }
                        is Resource.Error -> { Toast.makeText(LocalContext.current, (areasState.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()  }
                        is Resource.Success -> {
                            for (area in (areasState.value as Resource.Success).data) {
                                AreaListView(
                                    navController = navController,
                                    area = area,
                                    onClick = {mealViewModel.setArea(area)}
                                )
                            }
                        }
                    }
                } else if (topAppBarSelectedItem.value == INGREDIENT) {
                    when (ingredientsState.value) {
                        is Resource.Loading -> { CircularProgressIndicator() }
                        is Resource.Error -> { Toast.makeText(LocalContext.current, (ingredientsState.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()  }
                        is Resource.Success -> {
                            for (ingredient in (ingredientsState.value as Resource.Success).data) {
                                JIngredientListView(
                                    navController = navController,
                                    ingredient = ingredient,
                                    onClick = {mealViewModel.setIngredient(ingredient)}
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

fun myLambdaFunForDropDownMenu(selectedOption: String, listItems: List<String> = listOf("Kategorija", "Oblast", "Sastojci"), mealViewModel: MealContract.ViewModel) {
    if (selectedOption == listItems[0]) {
        mealViewModel.getCategories()
    } else if (selectedOption == listItems[1]) {
        mealViewModel.getAreas()

    } else if (selectedOption == listItems[2]) {
        mealViewModel.getIngredients()
    }
}
