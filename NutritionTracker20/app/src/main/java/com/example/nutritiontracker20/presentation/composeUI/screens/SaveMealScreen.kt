package com.example.nutritiontracker20.presentation.composeUI.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.DEFAULT_USERNAME
import com.example.nutritiontracker20.utils.eMealOfTheDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SaveMealScreen(
    mealViewModel: MealContract.ViewModel,
    userViewModel: UserContract.ViewModel,
    navController: NavController
) {
    val context = LocalContext.current.applicationContext
// ----------------------------------------------------------------------------
    val chosenMeal = mealViewModel.chosenMeal.observeAsState(Resource.Loading())
    var chosenDate by remember { mutableStateOf(LocalDate.now())}
    var timeOfDay by remember {mutableStateOf(eMealOfTheDay.BREAKFAST)} // neka bude BREAKFAST defaultni

    val savedMealState = mealViewModel.savedMealState.observeAsState()

    when(chosenMeal.value) {
        is Resource.Loading -> { CircularProgressIndicator() }
        is Resource.Error -> { Toast.makeText(context, (chosenMeal.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()}
        is Resource.Success -> {
            val tmpChosenMeal = (chosenMeal.value as Resource.Success).data
            LazyColumn(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                item {
                    Row(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = tmpChosenMeal.strMeal,
                            style = MaterialTheme.typography.h1
                        )
                    }
                }
                item {
                    // TODO KALORIJE OVDE
                    Text(text = "kcal", modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
                }
                item {
                    AsyncImage(
                        model = tmpChosenMeal.strMealThumb,
                        contentDescription = "foodies"
                    )
                }
                item{
                    ChooseDate { year, month, dayOfMonth ->
                        chosenDate = LocalDate.of(year, month+1, dayOfMonth)
                        Log.d("DATE", "chosenDate: $chosenDate")
                    }
                }
                // izgleda Breakfast, Lunch, Dinner nam ne treba
//                item{
//                    ChooseTimeOfDay{
//                        timeOfDay = it
//                        Log.d("MEAL OF THE DAY", "chosenTimeOfDay: $timeOfDay")
//                    }
//                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                mealViewModel.saveMealToFavorites(
                                    SavedMealsEntity(
                                        user = userViewModel.loggedUser.value?.username ?: DEFAULT_USERNAME,
                                        meal = tmpChosenMeal.strMeal,
                                        kcal = 0, //TODO KCAL
                                        tags = tmpChosenMeal.strTags,
                                        video = tmpChosenMeal.strYoutube,
                                        photo = tmpChosenMeal.strMealThumb,
                                        category = tmpChosenMeal.strCategory,
                                        region = tmpChosenMeal.strArea,
                                        instructions = tmpChosenMeal.strInstructions,
                                        date = Date.from(chosenDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                                    )
                                )
                            },
                            modifier = Modifier.padding()
                        ) {
                            Text (text="Save")
                        }
                        Button(onClick = {navController.popBackStack()}) {
                            Text(text="Cancel")
                        }
                    }
                }
                if(savedMealState.value != null) {
                    if(savedMealState.value == true) {
                        Toast.makeText(
                            context,
                            "Successfully saved meal: ${tmpChosenMeal.strMeal} for user: ${userViewModel.loggedUser.value?.username ?: DEFAULT_USERNAME}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if(savedMealState.value == false) {
                        Toast.makeText(
                            context,
                            "Meal: ${tmpChosenMeal.strMeal} not successfully saved!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

@Composable
fun ChooseDate(onDateChanged: (year: Int, month: Int, dayOfMonth: Int) -> Unit) {
    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.fillMaxWidth(),
        update = { views ->
            views.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateChanged(year, month, dayOfMonth)
            }
        }
    )
}

@Composable
fun ChooseTimeOfDay(
    onSelectOption: (eMealOfTheDay) -> Unit
) {
    val context = LocalContext.current.applicationContext
    val radioOptions = listOf(eMealOfTheDay.BREAKFAST, eMealOfTheDay.LUNCH, eMealOfTheDay.DINNER)

    val selectedOption = remember { mutableStateOf(radioOptions[0]) }
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(
        Modifier
            .selectableGroup()
            .padding(bottom = 26.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        radioOptions.forEach { radioOption ->
            Row(
                Modifier
                    .fillMaxWidth()
                    //.height(56.dp)
                    .selectable(
                        selected = (radioOption == selectedOption.value),
                        onClick = {
                            if (radioOption != selectedOption.value) {
                                // ako nisu isti znaci da je selected == false (TREBALO BI)
                                // sto znaci da mozemo promeniti vrednost
                                selectedOption.value = radioOption
                            }
                            onSelectOption(selectedOption.value)
                            Toast
                                .makeText(context, radioOption.name, Toast.LENGTH_SHORT)
                                .show()
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (radioOption == selectedOption.value),
                    onClick = { Toast.makeText(context, radioOption.name, Toast.LENGTH_SHORT).show() } // null recommended for accessibility with screenreaders
                )
                Text(
                    text = radioOption.name,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}