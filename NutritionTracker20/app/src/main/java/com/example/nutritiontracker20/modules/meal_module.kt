package com.example.nutritiontracker20.modules

import com.example.nutritiontracker20.data.db.MealDatabase
import com.example.nutritiontracker20.data.repositories.MealRepository
import com.example.nutritiontracker20.data.repositories.MealRepositoryImpl
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    viewModel { MealViewModel(mealRepository = get()) }

    single<MealRepository> { MealRepositoryImpl(savedMealDao = get()) }
    single { get<MealDatabase>().getSavedMealDao() }
}