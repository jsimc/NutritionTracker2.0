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
import coil.compose.AsyncImage
import com.example.nutritiontracker20.data.models.Category
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.domain.JIngredient
import com.example.nutritiontracker20.utils.MEALS_PAGE
import com.example.nutritiontracker20.utils.MEAL_DETAIL_PAGE

@Composable
fun KategorijaListView(navController: NavController, category: Category, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
            navController.navigate(MEALS_PAGE)
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = category.strCategory)
                MyPopup(info = category.strCategoryDescription)
            }
            AsyncImage(model = category.strCategoryThumb, contentDescription = "picture")
        }
    }
}

@Composable
fun MyPopup (info: String) {

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
            Text (text = info)
        }
    }
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun MealListView(meal: Meal, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Text(text = meal.strMeal)
                // mealViewModel.getMeal.getInfo --> onda MyPopup ne mora da prima mealViewModel
//            MyPopup(info = meal.strInstructions)
            AsyncImage(model = meal.strMealThumb, contentDescription = "slika")
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////

@Composable
fun AreaListView(navController: NavController, area: String, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
            navController.navigate(MEALS_PAGE)
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = area)
            }
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun JIngredientListView(navController: NavController, ingredient: JIngredient, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
            navController.navigate(MEALS_PAGE)
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = ingredient.strIngredient ?: "")
                MyPopup(info = ingredient.strDescription ?: "")
            }
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun FavMealListView(meal: String, photo: String, count: Int, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Text(text = meal)
            AsyncImage(model = photo, contentDescription = "slika")
            Text(text = "Count: $count")
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun FavMealCategoryListView(category: String, count: Int, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Text(text = category)
            Text(text = "Count: $count")
        }
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////
@Composable
fun FavMealAreaListView(area: String, count: Int, onClick: () -> Unit) {
    Surface(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .clickable {
            onClick()
        }
        .clip(MaterialTheme.shapes.medium)
    ) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Text(text = area)
            Text(text = "Count: $count")
        }
    }
}