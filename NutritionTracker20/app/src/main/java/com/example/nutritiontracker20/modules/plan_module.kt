package com.example.nutritiontracker20.modules

import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.presentation.viewmodels.PlanViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val planModule = module {
    viewModel { PlanViewModel() }
}
