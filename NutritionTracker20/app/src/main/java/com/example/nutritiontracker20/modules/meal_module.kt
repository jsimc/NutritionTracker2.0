package com.example.nutritiontracker20.modules

import com.example.nutritiontracker20.data.datasources.remote.MealService
import com.example.nutritiontracker20.data.db.MealDatabase
import com.example.nutritiontracker20.data.repositories.DBMealRepository
import com.example.nutritiontracker20.data.repositories.DBMealRepositoryImpl
import com.example.nutritiontracker20.data.repositories.JMealRepository
import com.example.nutritiontracker20.data.repositories.JMealRepositoryImpl
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    viewModel { MealViewModel(DBMealRepository = get()) }

    single<DBMealRepository> { DBMealRepositoryImpl(savedMealDao = get()) }
    single { get<MealDatabase>().getSavedMealDao() }
    single <JMealRepository> { JMealRepositoryImpl(mealService = get())}
    single <MealService> { create(retrofit = get()) }
}