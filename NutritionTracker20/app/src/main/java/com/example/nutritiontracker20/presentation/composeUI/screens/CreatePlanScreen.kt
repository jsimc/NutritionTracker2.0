package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.composeUI.elements.PlanGridView
import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.utils.CREATE_PLAN_MODE
import com.example.nutritiontracker20.utils.HOME_PAGE
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


@Composable
fun CreatePlanScreen(planViewModel: PlanContract.ViewModel, navController: NavController) {
    val context = LocalContext.current

    val mealMap = planViewModel.weekMealsMap.observeAsState()

    Column {
        TopAppBar() {
            Text(text = "Create Plan")
        }
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var email by remember { mutableStateOf("") }
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") })
                    PlanGridView(mealsMapa = mealMap.value) { chosenDayOfTheWeek, chosenMealOfTheDay ->
                        planViewModel.setChosenDayOfTheWeek(chosenDayOfTheWeek)
                        planViewModel.setChosenMealOfTheDay(chosenMealOfTheDay)
                        CREATE_PLAN_MODE = true
                        navController.navigate(HOME_PAGE)
                    }
                }
            }
            item {
                Button(onClick = { /*TODO posalji mejl*/
                    val toEmail = "nnikolic4820rn@raf.rs"
                    val fromEmail = "nutritionapp.raf@gmail.com"
                    val password = "JelenaINata"

                    val properties = Properties()
                    properties["mail.smtp.auth"] = "true"
                    properties["mail.smtp.starttls.enable"] = "true"
                    properties["mail.smtp.host"] = "smtp.gmail.com"
                    properties["mail.smtp.port"] = "587"

                    val session = Session.getInstance(properties, object : Authenticator(){
                        override fun getPasswordAuthentication(): PasswordAuthentication{
                            return PasswordAuthentication(fromEmail, password)
                        }
                    })
                    // kada kliknes send puca kada udje u ovaj try catch
                    try{
                        val message = MimeMessage(session)
                        message.setFrom(InternetAddress(fromEmail))
                        message.addRecipient(Message.RecipientType.TO, InternetAddress(toEmail))
                        message.subject = "Test"
                        message.setText("Poycina")

                        Transport.send(message)
                        println("Email sent successfully!")
                    } catch (e: MessagingException) {
                        e.printStackTrace()
                    }

                    planViewModel.clearMap()
                }) {
                    Text(text = "Send mail")
                }
            }
        }
    }
}