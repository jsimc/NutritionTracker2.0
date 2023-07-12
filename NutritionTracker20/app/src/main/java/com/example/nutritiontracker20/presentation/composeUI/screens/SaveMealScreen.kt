package com.example.nutritiontracker20.presentation.composeUI.screens

import android.annotation.SuppressLint
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.nutritiontracker20.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SaveMealScreen(navController: NavController) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current.applicationContext
    val showSnackbar: () -> Unit = {
        coroutineScope.launch {
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = "Sacuvano",
                actionLabel = "Undo"
            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> Toast.makeText(context, "dismissed", Toast.LENGTH_SHORT).show()
                SnackbarResult.ActionPerformed -> Toast.makeText(context, "action performed", Toast.LENGTH_SHORT).show()
            }
        }
    }
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
                androidx.compose.material.Text(
                    text = "Naziv jela",
                    style = MaterialTheme.typography.h1
                )
            }
        }
        item {
            androidx.compose.material.Text(text = "kcal", modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.hamburger),
                contentDescription = "foodies"
            )
        }
        item{
            ChooseDate()
        }
        item{
            ChooseTimeOfDay()
        }
        item {
            Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp).fillMaxWidth()) {

                    Button(onClick = {
                        showSnackbar()
                    }, modifier = Modifier.padding()) {
                        androidx.compose.material.Text (text="Save")
                    }
                    Button(onClick = {navController.popBackStack()}) {
                        androidx.compose.material.Text(text="Cancel")
                    }

            }
        }
    }

}

@Composable
fun ChooseDate() {
    val context = LocalContext.current.applicationContext

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf(LocalDate.now()) }


    AndroidView(
        { CalendarView(it) },
        modifier = Modifier.fillMaxWidth(),
        update = { views ->
            views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                mDate.value = LocalDate.of(year, month+1, dayOfMonth)
                /** TODO ovde se cuva datum treba da ga cuvamo negde zajednicko (viewModel) i onda kad se klikne
                 *      dugme -> sacuvaj onda se cuva u bazi  **/
                Toast.makeText(context, (mDate.value.dayOfMonth)+1, Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Composable
fun ChooseTimeOfDay() {
    val context = LocalContext.current.applicationContext
    // We have two radio buttons and only one can be selected
    val radioOptions = listOf("Breakfast", "Lunch", "Dinner")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
// Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup().padding(bottom = 26.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    //.height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            /**TODO ovde view model pa neka logika ----umesto Toast---**/
                            Toast
                                .makeText(context, text, Toast.LENGTH_SHORT)
                                .show()
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { Toast.makeText(context, text, Toast.LENGTH_SHORT).show() } // null recommended for accessibility with screenreaders
                )
                androidx.compose.material.Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}




//fun SnackbarDemo() {
//    val scaffoldState: ScaffoldState = rememberScaffoldState()
//    val coroutineScope: CoroutineScope = rememberCoroutineScope()
//
////    Scaffold(scaffoldState = scaffoldState) {
////        Button(onClick = {
//            coroutineScope.launch {
//                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
//                    message = "Saved!",
//                    actionLabel = "Undo"
//                )
//                when (snackbarResult) {
//                    SnackbarResult.Dismissed -> TODO("mealViewModel.removeMeal")
//                    SnackbarResult.ActionPerformed -> TODO("mealViewModel.addMeal")
//                }
//            }
////        }) {
////            Text(text = "Click me!")
////        }
////    }
//}
