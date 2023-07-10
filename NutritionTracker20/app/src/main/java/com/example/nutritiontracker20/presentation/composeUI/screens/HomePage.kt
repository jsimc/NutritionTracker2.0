package com.example.nutritiontracker20.presentation.composeUI.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.composeUI.elements.KategorijaListView
import com.example.nutritiontracker20.presentation.composeUI.elements.MyDropDownMenu
import com.example.nutritiontracker20.presentation.composeUI.elements.SearchBar
import com.example.nutritiontracker20.presentation.contracts.MealContract

@Composable
fun HomePage(mealViewModel: MealContract.ViewModel, navController: NavController) { //DI mealViewModel u pocetni ekran, HomePage nam je kao pocetni ekran
    val listItems = listOf("Kategorija", "Oblast", "Sastojci")
    Column {
        TopAppBar {
            //TODO ovde mozemo da ubacimo npr dugme gde mozemo videti sve favorite
            MyDropDownMenu(listItems = listItems, onClick = {}, modifier = Modifier.fillMaxWidth())
        }
        SearchBar(onSearch = {mealViewModel.searchCategory(it)})
//        TextField (modifier = Modifier.fillMaxWidth(), value = "",
//                onValueChange = {mealViewModel.search(it)})
        Row (horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Button (onClick = {}) {Text (text = "filter")}
            Button (onClick = {}) {Text (text = "sort")}
        }
        LazyColumn(modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.End, // End
            verticalArrangement = Arrangement.Center)
        {
            // mealViewModel.getCategories() --> I onda  u KategorijaLV prosledjujemo Kategoriju
            // al fora je sto nam treba I MEALVIEWMODEL u kategorijaLV zato sto moramo postaviti koja kategorija je OZNACENA
            // I sad sa ovom onClick lambdom ne moramo da prosledjujemo mealViewModel
            // MyPopup isto koristi mealViewModel --> da bi prikazao INFO, ali mi mozemo samo prosledjivati Kategoriju a ne ceo viewmodel
            // tako sve isto i za MealsPage
            items(10) {                                                         //samo sto ce IT da bude ta kategorija
                KategorijaListView(mealViewModel, navController, it, onClick = {mealViewModel.setKategorija(/*it*/)})
            }
        }

    }
}

