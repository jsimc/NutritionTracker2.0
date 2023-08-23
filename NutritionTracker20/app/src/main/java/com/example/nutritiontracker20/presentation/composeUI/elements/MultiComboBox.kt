package com.example.nutritiontracker20.presentation.composeUI.elements

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MultiComboBox(
    labelText: String,
    options: List<String>, //tagovi
    onOptionsChosen: (List<String>) -> Unit, //ovo je lambda funkcija koja ce da pomocu mealViewModela da filtrira dodatno
    modifier: Modifier = Modifier,
    selectedItems: List<String> = emptyList(),
) {
    var expanded by remember { mutableStateOf(false) }
    // when no options available, I want ComboBox to be disabled
    val isEnabled by rememberUpdatedState { options.isNotEmpty() }
    val selectedOptionsList  = remember { mutableStateListOf<String>()}

    //Initial setup of selected ids
    selectedItems.forEach{
        selectedOptionsList.add(it)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            if (isEnabled()) {
                expanded = !expanded
            }
        },
        modifier = modifier,
    ) {
        val selectedSummary = when (selectedOptionsList.size) {
            0 -> ""
            else -> {
                val sb = StringBuilder()
                selectedOptionsList.forEach { sb.append(it).append(" ") }
                sb.toString()
            }
        }
        Log.d("MultiComboBox", "selectedOptionsList: ${selectedOptionsList.toList()}")
        TextField(
            enabled = isEnabled(),
            readOnly = true,
            value = selectedSummary,
            onValueChange = {},
            label = { Text(text = labelText) }, //label = Tags
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onOptionsChosen(options.filter { it in selectedOptionsList }.toList())
            },
        ) {
            // tag in tags
            for (option in options) {

                //use derivedStateOf to evaluate if it is checked
                val checked = remember {
                    derivedStateOf{option in selectedOptionsList}
                }.value

                DropdownMenuItem(
                    onClick = {
                        if (!checked) {
                            selectedOptionsList.add(option)
                        } else {
                            selectedOptionsList.remove(option)
                        }
                    }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { newCheckedState ->
                                if (newCheckedState) {
                                    selectedOptionsList.add(option)
                                } else {
                                    selectedOptionsList.remove(option)
                                }
                            },
                        )
                        Text(text = option)
                    }
                }
            }
        }
    }
}