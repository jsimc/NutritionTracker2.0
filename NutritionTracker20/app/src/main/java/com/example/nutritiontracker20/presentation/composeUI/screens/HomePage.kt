package com.example.nutritiontracker20.presentation.composeUI.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.data.models.states.CategoriesState
import com.example.nutritiontracker20.presentation.composeUI.elements.KategorijaListView
import com.example.nutritiontracker20.presentation.composeUI.elements.MyDropDownMenu
import com.example.nutritiontracker20.presentation.composeUI.elements.SearchBar
import com.example.nutritiontracker20.presentation.contracts.MealContract

@Composable
fun HomePage(mealViewModel: MealContract.ViewModel, navController: NavController) { //DI mealViewModel u pocetni ekran, HomePage nam je kao pocetni ekran
    val listItems = listOf("Kategorija", "Oblast", "Sastojci")
    val categoriesState = mealViewModel.categoriesState.observeAsState(CategoriesState.Loading)

    Column {
        TopAppBar {
            //TODO ovde mozemo da ubacimo npr dugme gde mozemo videti sve favorite
            MyDropDownMenu(listItems = listItems, onClick = {}, modifier = Modifier.fillMaxWidth())
        }
        SearchBar(onSearch = {mealViewModel.searchCategory(it)})
//        TextField (modifier = Modifier.fillMaxWidth(), value = "",
//                onValueChange = {mealViewModel.search(it)})
        Row (horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Button (onClick = {}) { androidx.compose.material.Text (text = "filter") }
            Button (onClick = {}) { androidx.compose.material.Text (text = "sort") }
        }
        LazyColumn(modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, // End
            verticalArrangement = Arrangement.Center)
        {
            item{
                when (categoriesState.value) {
                    is CategoriesState.Loading -> { CircularProgressIndicator() }
                    is CategoriesState.Success -> {
                        for (category in (categoriesState.value as CategoriesState.Success).categories) {
                            KategorijaListView(
                                navController = navController,
                                category = category,
                                onClick = {mealViewModel.setKategorija(category)}
                            )
                        }
                    }
                    is CategoriesState.Error -> {
                        Toast.makeText(LocalContext.current, (categoriesState.value as CategoriesState.Error).message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(LocalContext.current, "Error: ${categoriesState.value}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}

