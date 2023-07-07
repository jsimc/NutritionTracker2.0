package com.example.nutritiontracker20.presentation.composeUI.elements

import android.icu.text.DateFormatSymbols
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@Composable
fun PlanGridView() {
    //Kad se klikne na plan treba da se otvori opet onaj pocetni ekran, ali posto ima u viewModelu BOOLEAN,
    // onda kad se otvori plan -> treba samo da se sacuva
    val dateFSymbols = DateFormatSymbols.getInstance()
    val weekdays = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val meals = listOf("Breakfast", "Lunch", "Dinner")
    val cardDates = remember { mutableStateListOf<LocalDate>() }
    val localDate = LocalDate.now()
    for (i in 0 until 6) {
        cardDates.add(localDate.plusDays(i.toLong()))
    }

    Column {
        Row(modifier=Modifier.padding(bottom=1.dp)){
            weekdays.forEach{
                HeaderCell(text = it)
            }
        }
        Row {
            weekdays.forEach { weekday ->
                Column {
                    meals.forEach {meal ->
                        MyCard(mealName = meal, weekday = weekday)
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderCell(text: String) {
    Card(
        modifier = Modifier
            .size(58.dp, 35.dp)
            .padding(horizontal=2.dp, vertical=4.dp)
    ){
        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically){
            Text(
                text = text,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun MyCard(mealName: String, weekday: String) {
    val context = LocalContext.current.applicationContext
    Card(
        modifier = Modifier
            .size(58.dp, 80.dp)
            .padding(horizontal=2.dp, vertical=4.dp)
            .clickable{
                  /**TODO
                   * mealViewModel.setMealTimeToBeSaved(mealName)
                   * mealViewModel.setDate(weekday) --> mogu i da napravim da je weekday Int, a takodje
                   * mi je bitno da ima i currDate u viewModelu, ali to mi treba onaj lazyRow iznad ovog grida
                   * **/
                Toast.makeText(context, weekday, Toast.LENGTH_SHORT).show()
            },
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(top = 1.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(mealName, fontSize = 9.sp)
        }
    }
}
