package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutritiontracker20.R

@Composable
fun ProfileScreen() {
    val userInfo = listOf("username", "password", "age", "gender", "weight", "height", "activity level")
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()) {
        item {
            Image(
                painter = painterResource(id = R.drawable.default_face),
                contentDescription = "profile",
                alignment = Alignment.Center
            )
        }
        //TODO
        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "username", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }
        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "password", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }

        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "age", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }

        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "gender", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }
        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "weight", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }
        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "height", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }

        item {
            /**viewModel.changeUsername()*/
            /**viewModel.changeUsername()*/
            RowItem(label = "activity level", placeholder = "", onChange = {/**viewModel.changeUsername()*/})
        }
    }
}

@Composable
fun RowItem(label: String, placeholder: String, onChange: (String)->Unit) {
    var textFieldValue by remember {
        mutableStateOf("")
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
            placeholder = { Text(text = placeholder) },
            modifier = Modifier.weight(1f)
        )
    }
}