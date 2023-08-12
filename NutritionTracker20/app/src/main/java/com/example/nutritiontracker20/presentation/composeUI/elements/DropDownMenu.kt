package com.example.nutritiontracker20.presentation.composeUI.elements

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterialApi::class)
@Composable
//Da se prosledjuje i modifier!
fun MyDropDownMenu(listItems: List<Any>, modifier: Modifier, firstSelected: Int = 0, selectedOption: MutableState<Any> = mutableStateOf(listItems[firstSelected]), onClick: (Any?) -> Unit) {
//    val listItems = arrayOf("Kategorija", "Oblast", "Sastojci")
    val contextForToast = LocalContext.current.applicationContext
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
//    var selectedItem by remember {
//        mutableStateOf(listItems[firstSelected]?:listItems[0])
//    }

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
            value = selectedOption.value.toString(),
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
            listItems.forEach { selectedOptionn ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedOption.value = selectedOptionn
                    // TODO  JOS nesto se dogadja ovde!? -- moram da napravim da se lazyColumn menja
                    onClick(selectedOption.value)
                    Toast.makeText(contextForToast, selectedOption.value.toString(), Toast.LENGTH_SHORT).show()
                    expanded = false
                }, modifier = Modifier.clip(MaterialTheme.shapes.medium)) {
                    Text(text = selectedOptionn.toString())
                }
            }
        }
    }
}
