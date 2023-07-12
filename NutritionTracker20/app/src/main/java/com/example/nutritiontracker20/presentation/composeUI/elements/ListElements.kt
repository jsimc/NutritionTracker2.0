package com.example.nutritiontracker20.presentation.composeUI.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.MEALS_PAGE
import com.example.nutritiontracker20.utils.MEAL_DETAIL_PAGE

@Composable
fun KategorijaListView(mealViewModel: MealContract.ViewModel, navController: NavController, num: Int, onClick: (String) -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable(true) {
            // mozda da u mealViewModel imamo jedan chosenKategorija
            // kako bismo znali sta da prikazemo u sledecem skrinu
            // TODO on je klikabilni element
            onClick(mealViewModel.chosenCategory.value.toString())
            navController.navigate(MEALS_PAGE)
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Kategorija".plus(num))
                //mealViewModel . getCategory.getInfo ?
                MyPopup(mealViewModel)
            }
            Text(text = "slika")
        }
    }
}

@Composable
fun MyPopup (mealViewModel: MealContract.ViewModel) {

    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.Info, contentDescription = "Localized description")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.padding(10.dp)
        ) {
            // mealViewModel.
            Text (text = "INFOOOO")
        }
    }
}

@Composable
fun MealListView(mealViewModel: MealContract.ViewModel, navController: NavController, num: Int) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize().clickable(true) {
        // mozda da u mealViewModel imamo jedan chosenKategorija
        // kako bismo znali sta da prikazemo u sledecem skrinu
        // TODO
        //  onClick
            if(/*mealViewModel.isCreatePlanRegime()*/true) {
                // TODO onda se ne navigira na MEAL_DETAIL_PAGE vec se vraca na
                //  createPlanScreen I dodaje plan za dan koji je bio kliknut, moramo smisliti kako proslediti taj dan
                //  vrv isto preko mealViewModela.
//                navController.navigate(CREATE_PLAN_SCREEN) {
//                    popUpTo(HOME_PAGE) {
//                        inclusive = true
//                    }
//                }
            }
        navController.navigate(MEAL_DETAIL_PAGE)
    }.clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Text(text = "Jelo".plus(num))
                // mealViewModel.getMeal.getInfo --> onda MyPopup ne mora da prima mealViewModel
//                MyPopup(mealViewModel)
            Text(text = "slika")
        }
    }
}