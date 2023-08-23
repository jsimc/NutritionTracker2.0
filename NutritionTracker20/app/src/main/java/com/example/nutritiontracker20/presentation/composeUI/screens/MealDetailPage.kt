package com.example.nutritiontracker20.presentation.composeUI.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImage
import com.example.nutritiontracker20.presentation.composeUI.theme.DrugaGlavnaBoja
import com.example.nutritiontracker20.presentation.composeUI.theme.GlavnaBoja
import com.example.nutritiontracker20.R
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.presentation.composeUI.elements.HyperlinkText
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.SAVE_MEAL_SCREEN

@Composable
fun MealDetailPage (
    mealViewModel: MealContract.ViewModel,
    navController: NavController
) {

    val chosenMeal = mealViewModel.chosenMeal.observeAsState(Resource.Loading())


////////////////////////////////////////////////////////
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { selectedImageUri = it }
    )
////////////////////////////////////////////////////////
    when(chosenMeal.value) {
        is Resource.Loading -> { CircularProgressIndicator() }
        is Resource.Error -> { Toast.makeText(LocalContext.current, (chosenMeal.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()  }
        is Resource.Success -> {
            val tmpChosenMeal = (chosenMeal.value as Resource.Success).data
            LazyColumn(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
            ) {

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(.5f))
                        Text(
                            text = tmpChosenMeal.strMeal, //name
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.h1
                        )
                        Spacer(modifier = Modifier.weight(.3f))
                        Icon(Icons.Default.Favorite, contentDescription = "Localized description",
                            modifier = Modifier.clickable(true) {/*TODO AKO je viewModelFlag = true onda ga odma cuva,
                                                            AKO je false onda odlazi na novu stranicu gde se bira dan i vreme (dorucak, uzina1, rucak, uzina2, vecera)*/
                                navController.navigate(SAVE_MEAL_SCREEN)
                            })
                    }
                }
                // KALORIJE TREBA IZRACUNATI
                item {
                    Text(text = "kcal", modifier = Modifier.padding(top = 10.dp, bottom = 5.dp))
                }
                //KATEGORIJA
                item {
                    Text(
                        text = tmpChosenMeal.strCategory, //ako je strCategory null onda mzoemo da uzmemo chosenCat iz mealVm
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                //OBLAST
                item {
                    Text(
                        text = tmpChosenMeal.strArea, //Oblast
                        style = MaterialTheme.typography.h2,
                        modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
                    )
                }
                item {
                    if (selectedImageUri == null) {
                        AsyncImage(
                            model = tmpChosenMeal.strMealThumb,
                            contentDescription = "foodies",
                            modifier = Modifier.clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                        )
                    } else {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = "foodies",
                            modifier = Modifier.clickable {
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                        )
                    }
                }

                // INGREDIENTS
                //TODO Nije mi jasno kako ingredients
                //                items(tmpChosenMeal.ingredients) {
//                    /**TODO mealViewModel.getIngredient.nestonesto*/
//                    /**TODO mealViewModel.getIngredient.nestonesto*/
//                    IngredientListView("Sastojak$it", onClick = {/**TODO mealViewModel.getIngredient.nestonesto*/})
//                }
                items(items = tmpChosenMeal.ingredients.keys.toList()) {
                    IngredientListView(name = it, measure = tmpChosenMeal.ingredients[it]!!) {

                    }
                }
                // INSTRUCTIONS
                item {
                    Text(text = tmpChosenMeal.strInstructions)
                }
                //TODO proveri da li radi
                // VIDEO
                item {
                    if(tmpChosenMeal.strYoutube != "") {
                        HyperlinkText(
                            fullText = "Watch video tutorial",
                            linkText = listOf("Watch video tutorial"),
                            hyperlinks = listOf(tmpChosenMeal.strYoutube)
                        )
                    }
                }
                // TAGOVI - string razdvojen po zarezu
                item {
                    LazyRow() {
                        items(items = tmpChosenMeal.strTags.split(",")) {
                            Text(text = it,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp, top = 8.dp)
                                    .background(
                                        Brush.linearGradient(listOf(GlavnaBoja, DrugaGlavnaBoja)),
                                        MaterialTheme.shapes.small
                                    )
                            )
                        }
                    }
                }
            }
        }
        else -> {}
    }
}

@Composable
fun IngredientListView(name: String = "Name: not available", measure: String = "Measure: not available", kcal: String = "Kcal: not available", onClick: ()->Unit) { //Kcal: not available
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(5.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(Brush.linearGradient(listOf(GlavnaBoja, DrugaGlavnaBoja)))
            .fillMaxWidth()
            .clickable(true) { onClick() }
    ) {
        // mozda mini slika sastojka?
        Text (text = name, modifier = Modifier
            .padding(15.dp)
            .weight(1f))
        Text (text = measure, modifier = Modifier
            .padding(15.dp)
            .weight(1f))
        Text (text = kcal, modifier = Modifier
            .padding(15.dp)
            .weight(1f))
    }
}