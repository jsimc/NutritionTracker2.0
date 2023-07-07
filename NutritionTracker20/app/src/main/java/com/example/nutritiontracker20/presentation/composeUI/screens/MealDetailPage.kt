package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.composeUI.theme.DrugaGlavnaBoja
import com.example.nutritiontracker20.presentation.composeUI.theme.GlavnaBoja
import com.example.nutritiontracker20.R
import com.example.nutritiontracker20.utils.SAVE_MEAL_SCREEN

@Composable
fun MealDetailPage (navController: NavController) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.weight(0.5f))
                MyText(
                    text = "Naziv jela",
                    modifier = Modifier.weight(0.8f),
                    style = MaterialTheme.typography.h1
                )
                Icon(Icons.Default.Favorite, contentDescription = "Localized description",
                    modifier = Modifier.clickable(true) {/*TODO AKO je viewModelFlag = true onda ga odma cuva,
                                                            AKO je false onda odlazi na novu stranicu gde se bira dan i vreme (dorucak, uzina1, rucak, uzina2, vecera)*/
                        navController.navigate(SAVE_MEAL_SCREEN)
                    })
            }
        }
        item {
            MyText(text = "kcal", modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
        }
        item {
            MyText(
                text = "Kategorija",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
            )
        }
        item {
            MyText(
                text = "Oblast",
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.hamburger),
                contentDescription = "foodies"
            )
        }

        items(7) {
            /**TODO mealViewModel.getIngredient.nestonesto*/
            /**TODO mealViewModel.getIngredient.nestonesto*/
            IngredientListView("Sastojak$it", onClick = {/**TODO mealViewModel.getIngredient.nestonesto*/})
        }

        item {
            MyText(text = "Recipe")
        }
        item {
            LazyRow() {
                items(3) {
                    //jel su tagovi klikabilni ?
                    Text(text = "tag$it",
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp, top = 8.dp)
                                        .background(Brush.linearGradient(listOf( GlavnaBoja, DrugaGlavnaBoja)), MaterialTheme.shapes.small)
                    )
                }
            }
        }
        //        AsyncImage(
        //            model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiC5R3A3ovB6e3vx5HBZX6C_qQXY4nJMMlMw&usqp=CAU",
        //            contentDescription = "Cutiesss"
        //        )
    }
}

@Composable
fun IngredientListView(name: String = "Name: not available", kcal: String = "Kcal: not available", onClick: ()->Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(5.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Brush.linearGradient(listOf( GlavnaBoja, DrugaGlavnaBoja)))
            .fillMaxWidth()
            .clickable(true) {onClick()}
    ) {
        // mozda mini slika sastojka?
        Text (text = name, modifier = Modifier.padding(15.dp))
        Text (text = kcal, modifier = Modifier.padding(15.dp))
    }
}

@Composable
fun MyText(text: String = "not available",
           modifier: Modifier = Modifier,
           color: Color = Color.Unspecified,
           fontSize: TextUnit = TextUnit.Unspecified,
           fontStyle: FontStyle? = null,
           fontWeight: FontWeight? = null,
           fontFamily: FontFamily? = null,
           letterSpacing: TextUnit = TextUnit.Unspecified,
           textDecoration: TextDecoration? = null,
           textAlign: TextAlign? = null,
           lineHeight: TextUnit = TextUnit.Unspecified,
           overflow: TextOverflow = TextOverflow.Clip,
           softWrap: Boolean = true,
           maxLines: Int = Int.MAX_VALUE,
           onTextLayout: (TextLayoutResult) -> Unit = {},
           style: TextStyle = LocalTextStyle.current) {
    Text (text = text, modifier = modifier, color, fontSize, fontStyle, fontWeight, fontFamily, letterSpacing, textDecoration, textAlign, lineHeight)
}