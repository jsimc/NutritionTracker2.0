package com.example.nutritiontracker20.presentation.composeUI.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.presentation.composeUI.theme.GlavnaBoja
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAccessor
import java.util.*

@Composable
fun Statistics(/*mealViewModel: MealContract.ViewModel */savedMeals: List<SavedMealsEntity>) {
    val linePlotLines = remember { mutableStateListOf<LinePlot.Line>() }
//    val pointValues = remember { mutableStateListOf<DataPoint>() }
    val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun") // x coord
    var xCoorIndex = 0f


    LaunchedEffect(key1 = savedMeals) {
        linePlotLines.clear()
        val dataPoints = arrayListOf<DataPoint>()
        val dataList = calculateDataList(savedMeals, days)

        dataList.forEach { kcal ->
            dataPoints.add(DataPoint(x = xCoorIndex, y = kcal.toFloat()))
            xCoorIndex+=1f
        }
        if(dataPoints.isNotEmpty()) {
            linePlotLines.add(
                LinePlot.Line(
                    dataPoints,
                    LinePlot.Connection(color = Color.Gray, strokeWidth = 2.dp),
                    LinePlot.Intersection(color = Color.Black, radius = 3.dp),
                    LinePlot.Highlight(color = Color.Blue)
                )
            )
        }
    }
    if (linePlotLines.isNotEmpty()) {
        LineGraph(
            plot = LinePlot(
                lines = linePlotLines,
                grid = LinePlot.Grid(Color.Cyan, steps = 4),
                selection = LinePlot.Selection(
                    enabled = true,
                    highlight = LinePlot.Connection(
                        color = GlavnaBoja,
                        strokeWidth = 1.dp,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f))
                    )
                )
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    } else {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Can't plot it: $savedMeals")
        }
    }
}

private fun calculateDataList(savedMeals: List<SavedMealsEntity>, days: Array<String>): List<Int> {
    val resultList: MutableList<Int> = MutableList(7) { it }

    savedMeals.forEach {
        val weekDay = convertDateToLocalDate(it.date).dayOfWeek.value
        resultList[weekDay-1] = resultList[weekDay.minus(1)] + it.kcal
    }
    return resultList
}

fun convertDateToLocalDate(date: Date): LocalDate {
    val instant = date.toInstant()
    return instant.atZone(ZoneId.systemDefault()).toLocalDate()
}
