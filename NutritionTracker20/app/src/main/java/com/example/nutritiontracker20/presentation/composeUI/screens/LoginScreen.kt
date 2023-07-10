package com.example.nutritiontracker20.presentation.composeUI.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.MAIN_GRAPH
import kotlinx.coroutines.currentCoroutineContext


@Composable
fun LoginScreen(navController: NavController, userViewModel: UserContract.ViewModel) {
    val context = LocalContext.current.applicationContext
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("loggedIn", Context.MODE_PRIVATE)

    val username = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(text = "Login", style = TextStyle(fontSize = 40.sp))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    // ovo je kao proverava da li ga ima u bazi --> mogao bi da vrati usera ako jeste u bazi.
//                    val user: User = userViewModel.checkForUser(username, password) --> vraca usera ako ga ima, ako ga nema
                    if(userViewModel.checkForUser(username = username.value, password = password.value)) {
//                        userViewModel.setHelperName(username.value)
//                        userViewModel.setUsername(username.value)
//                        userViewModel.setPassword(password.value)


                        // TODO
                        //  NE ZNAM ZASTO ALI BAGUJE NA OVOM DELU, ako se ovo zakomentarise, onda ne pravi problem sa Threadovima
                        val edit = sharedPreferences.edit()
                        edit.putBoolean("user_logged_in", true)
                        edit.putString("username", username.value)
                        edit.putString("password", password.value)
                        edit.apply()

                        navController.popBackStack()
                        navController.navigate(MAIN_GRAPH)
                    } else {
                        Toast.makeText(context, "Wrong credentials!", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}