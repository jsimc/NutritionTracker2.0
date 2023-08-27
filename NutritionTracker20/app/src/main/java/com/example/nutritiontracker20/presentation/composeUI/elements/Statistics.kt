package com.example.nutritiontracker20.presentation.composeUI.elements

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.presentation.composeUI.theme.GlavnaBoja
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAccessor
import java.util.*

@Composable
fun Statistics(mealViewModel: MealContract.ViewModel, /*savedMeals: List<SavedMealsEntity>,*/ lambdaFunction: () -> Unit) {
    val linePlotLines = remember { mutableStateListOf<LinePlot.Line>() }
//    val pointValues = remember { mutableStateListOf<DataPoint>() }
    val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun") // x coord
    var xCoorIndex = 0f

    val mealsForGraph = mealViewModel.mealsForGraph.observeAsState(Resource.Loading())
    DisposableEffect(Unit) {
        // This block of code will run when the composable is first composed
        // Perform any actions or initialization here
        mealViewModel.getAllForGraph()

        // Dispose of any resources if needed when the composable leaves the composition
        onDispose {
            // This block of code will run when the composable is removed
            // Perform cleanup or resource release here
        }
    }

    when(mealsForGraph.value) {
        is Resource.Loading -> { CircularProgressIndicator() }
        is Resource.Error -> {
            Toast.makeText(LocalContext.current, (mealsForGraph.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
        }
        is Resource.Success -> {
            val savedMeals = (mealsForGraph.value as Resource.Success).data
            LaunchedEffect(key1 = mealsForGraph) {
                linePlotLines.clear()
                val dataPoints = arrayListOf<DataPoint>()
                val dataList = calculateDataList(savedMeals, days)

                dataList.forEach { numOfMeals ->
                    dataPoints.add(DataPoint(x = xCoorIndex, y = numOfMeals.toFloat()))
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
    }
}

private fun calculateDataList(savedMeals: List<SavedMealsEntity>, days: Array<String>): List<Int> {
    val resultList: MutableList<Int> = MutableList(7) { 0 }

    savedMeals.forEach {
        val weekDay = convertDateToLocalDate(it.date).dayOfWeek.value
        resultList[weekDay-1] = resultList[weekDay.minus(1)] + 1 // it.kcal
        Log.d("Y- OSA", "$weekDay")
    }
    return resultList
}

fun convertDateToLocalDate(date: Date): LocalDate {
    val instant = date.toInstant()
    return instant.atZone(ZoneId.systemDefault()).toLocalDate()
}
