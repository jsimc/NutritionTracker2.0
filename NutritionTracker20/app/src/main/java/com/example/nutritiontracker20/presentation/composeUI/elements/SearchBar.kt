package com.example.nutritiontracker20.presentation.composeUI.elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var searchBy by remember {
        mutableStateOf("")
    }
    Row (modifier = Modifier.fillMaxWidth()) {
        TextField (value = searchBy,
            onValueChange = { searchBy = it
                                onSearch(searchBy) },
            modifier = Modifier.weight(1f),
            leadingIcon = { Icon(Icons.Default.Search, null)},
            label = { Text(text = "Search")})
    }
//    TextField(
//        value = text,
//        onValueChange = { text =  },
//        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
//        modifier = Modifier.fillMaxWidth(),
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
//        keyboardActions = KeyboardActions(onSearch = {
//            onSearch(text)
//            // Hide the keyboard after submitting the search
//            keyboardController?.hide()
//            //or hide keyboard
//            focusManager.clearFocus()
//
//        })
//    )
}