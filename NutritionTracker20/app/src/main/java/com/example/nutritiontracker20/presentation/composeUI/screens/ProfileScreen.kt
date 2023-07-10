package com.example.nutritiontracker20.presentation.composeUI.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutritiontracker20.R
import com.example.nutritiontracker20.data.models.User
import com.example.nutritiontracker20.presentation.composeUI.elements.MyDropDownMenu
import com.example.nutritiontracker20.presentation.composeUI.graphs.RootNavigationGraph
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.INTRO_GRAPH
import com.example.nutritiontracker20.utils.LOGIN_SCREEN
import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender
import java.net.UnknownServiceException

@Composable
fun ProfileScreen(userViewModel: UserContract.ViewModel, navController: NavController) {
//    val lifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("loggedIn", Context.MODE_PRIVATE)
    val genders = listOf(eGender.FEMALE, eGender.MALE, eGender.OTHER)
    val activities = listOf(eActivity.LittleToNoExercise, eActivity.OneOrTwoTimes, eActivity.ThreeOrFourTimes, eActivity.FiveOrMoreTimes)

    val username = sharedPreferences.getString("username", "jelena")?: "jelena"

    // moze li neesto tipa:
//    val age by remember { userViewModel.getAge(username) }
    // Pitaj Natu, glupa si JELENAAAAA


    // npr
//    val user: User = userViewModel.getUserByUsername(username)
    // i onda -> user.age, user.weekly
    // samo mi treba da funkcija iz userViewModela.

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            // ocemo li i sliku cuvati u bazi? --> ??
            Image(
                painter = painterResource(id = R.drawable.default_face),
                contentDescription = "profile",
                alignment = Alignment.Center
            )
        }
        //TODO
        // username
        item {
            // TODO OMFGH RADI
            // ali username ne treba da moze da se menja!
            RowItemTextField(label = "username", initialString = sharedPreferences.getString("username", "")?:"", readOnly = true, onChange = {})
        }
        // Nema menjanja lozinke bas me briga
        // password
        item {
            RowItemTextField(label = "password", initialString = sharedPreferences.getString("password", "")?:"", readOnly = true, onChange = {})
        }

        item {
            RowItemTextField(label = "age", initialString = /*age?.toString() ?: */"Not available", onChange = {})
        }
// GENDER
        item {
            RowItemDropDown(label = "gender", listItems = genders, onClick = { })
        }
        item {
            RowItemTextField(label = "weight", initialString = /*weight?.toString() ?:*/ "Not available", onChange = {})
        }
        item {
            RowItemTextField(label = "height", initialString = /*height?.toString() ?: */"Not available", onChange = {})
        }

// ACTIVITY drop down menu
        item {
            RowItemDropDown(label = "activity level", listItems = activities,  onClick = {
                println("ITTTTT: $it")
                userViewModel.updateWeeklyActivity(username, it as eActivity)})
        }

        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItemTextField(label = "Recommended calory intake", initialString = /*kcalIntake?.toString() ?:*/ "Not available", readOnly = true, onChange = {})
        }

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()) {
                Button(onClick = {
//                    userViewModel.updateInfo()

                }) {
                    Text(text="Save user info")
                }
                Button(onClick = {
                    sharedPreferences.edit().clear().apply()
                    navController.popBackStack()
                    //nema ga! na ovom kontroleru!
//                    RootNavigationGraph(navController = , mealViewModel = , userViewModel = )
                    navController.navigate(INTRO_GRAPH)
                }) {
                    Text(text="Log off")
                }
            }
        }
    }
}

@Composable
fun RowItemTextField(label: String, initialString: String, readOnly: Boolean = false, onChange: (String)->Unit) {
    var textFieldValue by remember {
        mutableStateOf(initialString)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            style = TextStyle(fontSize = 16.sp)
        )
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onChange(textFieldValue)},
            modifier = Modifier.weight(1f),
            readOnly = readOnly
        )
    }
}

@Composable
fun RowItemDropDown(label: String, listItems: List<Any>, onClick: (Any?)->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            style = TextStyle(fontSize = 16.sp)
        )
        MyDropDownMenu(listItems = listItems, onClick = onClick, modifier = Modifier.weight(1f))

    }
}

