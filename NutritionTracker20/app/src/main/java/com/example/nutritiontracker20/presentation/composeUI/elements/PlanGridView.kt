package com.example.nutritiontracker20.presentation.composeUI.elements

import androidx.compose.foundation.background
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
import java.time.format.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutritiontracker20.data.models.MealPlan
import com.example.nutritiontracker20.presentation.composeUI.theme.GlavnaBoja
import com.example.nutritiontracker20.utils.eMealOfTheDay
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

@Composable
fun PlanGridView(mealsMapa: MutableMap<String, MealPlan>? = mutableMapOf(), onClick: (chosenDayOfTheWeek: DayOfWeek, chosenMealOfTheDay: eMealOfTheDay) -> Any) {
    val weekdays = listOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    val meals = listOf(eMealOfTheDay.BREAKFAST, eMealOfTheDay.LUNCH, eMealOfTheDay.DINNER)
    val cardDates = remember { mutableStateListOf<LocalDate>() }
    val localDate = LocalDate.now()
    for (i in 0 until 6) {
        cardDates.add(localDate.plusDays(i.toLong()))
    }

    Column {
        Row(modifier=Modifier.padding(bottom=1.dp)){
            weekdays.forEach{
                HeaderCell(text = it.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
            }
        }
        Row {
            weekdays.forEach { weekday ->
                Column {
                    meals.forEach {meal ->
                        MyCard(mealOfTheDay = meal, color = if (mealsMapa != null && mealsMapa.contains(weekday.name) && mealsMapa[weekday.name]!!.mealOfTheDay == meal) Color.Green else GlavnaBoja) {
                            onClick(weekday, meal) // prosledjuje se MyCard()
                        }
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
fun MyCard(mealOfTheDay: eMealOfTheDay, color: Color = GlavnaBoja, onClick: () -> Any) {
    Card(
        modifier = Modifier
            .size(58.dp, 80.dp)
            .padding(horizontal=2.dp, vertical=4.dp)
            .clickable{
                onClick()
            },
        elevation = 10.dp
    ) {
        Row(
            modifier = Modifier.background(color).padding(top = 1.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(mealOfTheDay.name, fontSize = 9.sp)
        }
    }
}
