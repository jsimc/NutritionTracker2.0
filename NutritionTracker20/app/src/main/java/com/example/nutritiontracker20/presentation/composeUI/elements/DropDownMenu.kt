package com.example.nutritiontracker20.presentation.composeUI.elements

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
//Da se prosledjuje i modifier!
fun MyDropDownMenu(listItems: List<Any>, modifier: Modifier, firstSelected: Int = 0, onClick: (Any?) -> Unit) {
//    val listItems = arrayOf("Kategorija", "Oblast", "Sastojci")
    val contextForToast = LocalContext.current.applicationContext
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(listItems[firstSelected]?:listItems[0])
    }

    // box
    ExposedDropdownMenuBox(
        modifier = modifier,
//        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        TextField(
            modifier = modifier,
//            modifier = Modifier.fillMaxWidth(),
            value = selectedItem.toString(),
            onValueChange = {},
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = selectedOption
                    // TODO  JOS nesto se dogadja ovde!?
                    onClick(selectedOption)
                    Toast.makeText(contextForToast, selectedOption.toString(), Toast.LENGTH_SHORT).show()
                    expanded = false
                }, modifier = Modifier.clip(MaterialTheme.shapes.medium)) {
                    Text(text = selectedOption.toString())
                }
            }
        }
    }
}
