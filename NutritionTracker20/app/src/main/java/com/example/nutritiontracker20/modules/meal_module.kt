package com.example.nutritiontracker20.modules

import com.example.nutritiontracker20.data.datasources.remote.CategoryService
import com.example.nutritiontracker20.data.datasources.remote.MealService
import com.example.nutritiontracker20.data.db.MealDatabase
import com.example.nutritiontracker20.data.repositories.*
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    viewModel { MealViewModel(mealRepository = get(), jCategoryRepository = get()) }

    single<MealRepository> { MealRepositoryImpl(savedMealDao = get(), mealService = get()) }
    single { get<MealDatabase>().getSavedMealDao() }
//    single <JMealRepository> { JMealRepositoryImpl(mealService = get())}
    single <MealService> { create(retrofit = get()) }

    single <JCategoryRepository> { JCategoryRepositoryImpl(categoryService = get())}
    single <CategoryService> { create(retrofit = get ()) }
}