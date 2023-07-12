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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutritiontracker20.R
import com.example.nutritiontracker20.data.models.User
import com.example.nutritiontracker20.presentation.composeUI.elements.MyDropDownMenu
import com.example.nutritiontracker20.presentation.composeUI.elements.Statistics
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.*

@Composable
fun ProfileScreen(userViewModel: UserContract.ViewModel, navController: NavController) {
//    val lifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("loggedIn", Context.MODE_PRIVATE)
    val genders = listOf(eGender.FEMALE, eGender.MALE, eGender.OTHER)
    val activities = listOf(eActivity.LittleToNoExercise, eActivity.OneOrTwoTimes, eActivity.ThreeOrFourTimes, eActivity.FiveOrMoreTimes)

    val tmpObs = userViewModel.tmp.observeAsState()
    
    val user = userViewModel.loggedUser.observeAsState(/*initial = User(
        DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_AGE, DEFAULT_HEIGHT, DEFAULT_WEIGHT, DEFAULT_GENDER, DEFAULT_WEEKLY_ACTIVITY
    )*/)
    user.value?.suggestedKcal = kcalCalculator(user.value?.gender!!, user.value?.height!!, user.value?.weight!!, user.value?.age!!)

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.default_face),
                contentDescription = "profile",
                alignment = Alignment.Center
            )
        }
        //TODO
// USERNAME
        item {
            // TODO OMFGH RADI
            // ali username ne treba da moze da se menja!
            RowItemTextField(label = "username", initialValue = user.value?.username ?: "Not available", readOnly = true, onChange = {})
        }
        // Nema menjanja lozinke bas me briga
// PASSWORD
        item {
            RowItemTextField(label = "password", initialValue = user.value?.password ?: "Not available", readOnly = true, onChange = {})
        }
// AGE
        item {
            RowItemTextField(label = "age (in years)", initialValue = user.value?.age?.toString() ?: "Not available", initial = user, onChange = {
                if (it == "") userViewModel.loggedUser.value?.age = 0 //user.value?.age = 0
                else userViewModel.loggedUser.value?.age = it.toInt() //user.value?.age = it.toInt()
            })
        }
// GENDER
        item {
            RowItemDropDown(label = "gender", listItems = genders, firstSelected = user.value?.gender) { user.value?.gender = it as eGender }
        }
// WEIGHT
        item {
            RowItemTextField(label = "weight (in kg)", initialValue = user.value?.weight?.toString() ?: "Not available", initial = user, onChange = {
                if (it == "") {user.value?.weight = 0}
                else {user.value?.weight = it.toInt()}
                println(user.value!!.weight)})
        }
// HEIGHT
        item {// ovo updateuje
            RowItemTextField(label = "height (in cm)", initialValue = user.value?.height?.toString() ?: "Not available", initial = user, onChange = {
                if (it == "") user.value?.height = 0
                else user.value?.height = it.toInt() })
        }

// ACTIVITY drop down menu
        item {
            RowItemDropDown(label = "activity level", listItems = activities, firstSelected = user.value?.weeklyActivity) {
                user.value?.weeklyActivity = it as eActivity
            }
        }
// KCAL INTAKE
        item {
            RowItemTextField(label = "Recommended calory intake", initialValue = user.value?.suggestedKcal.toString() ?: "Not available", initial = user, forKcalBool = true, readOnly = true, onChange = {})
        }
        
        item {
            Statistics(savedMeals = SAVED_MEALS)
        }

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()) {
                Button(onClick = {
                  val newUser = 
                      User(user.value!!.username, user.value!!.password, user.value!!.age, user.value!!.height, 
                          user.value!!.weight, user.value!!.gender, user.value!!.weeklyActivity, kcalCalculator(user.value?.gender!!, user.value?.height!!, user.value?.weight!!, user.value?.age!!))
                    userViewModel.updateUser(newUser)
                    println("newUser: $newUser")
                }) {
                    Text(text="Save user info")
                }
                Button(onClick = {
                    sharedPreferences.edit().clear().apply()
                    navController.popBackStack()
                    navController.navigate(INTRO_GRAPH)
                }) {
                    Text(text="Log off")
                }
            }
        }
    }
}

@Composable
fun RowItemTextField(label: String, initialValue: String, initial: State<User?>? = null, forKcalBool: Boolean = false, readOnly: Boolean = false, onChange: (String)->Unit) {
    var textFieldValue by remember {
        mutableStateOf(initialValue)
    }

    if (forKcalBool) {
        textFieldValue = initial!!.value!!.suggestedKcal.toString()
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
fun RowItemDropDown(label: String, listItems: List<Any>, firstSelected: Any?, onClick: (Any?)->Unit) {
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

        MyDropDownMenu(listItems = listItems, onClick = onClick, firstSelected = listItems.indexOf(firstSelected), modifier = Modifier.weight(1f))

    }
}

