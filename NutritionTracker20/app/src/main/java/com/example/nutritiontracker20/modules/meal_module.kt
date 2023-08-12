package com.example.nutritiontracker20.modules

import com.example.nutritiontracker20.data.datasources.remote.AreaService
import com.example.nutritiontracker20.data.datasources.remote.CategoryService
import com.example.nutritiontracker20.data.datasources.remote.IngredientService
import com.example.nutritiontracker20.data.datasources.remote.MealService
import com.example.nutritiontracker20.data.db.MealDatabase
import com.example.nutritiontracker20.data.repositories.*
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {
    viewModel { MealViewModel(mealRepository = get(), jCategoryRepository = get(), jAreaRepository = get(), jIngredientRepository = get()) }

    single<MealRepository> { MealRepositoryImpl(savedMealDao = get(), mealService = get()) }
    single { get<MealDatabase>().getSavedMealDao() }
//    single <JMealRepository> { JMealRepositoryImpl(mealService = get())}
    single <MealService> { create(retrofit = get()) }

    single <JCategoryRepository> { JCategoryRepositoryImpl(categoryService = get())}
    single <CategoryService> { create(retrofit = get ()) }

    single <JAreaRepository> { JAreaRepositoryImpl(areaService = get())}
    single <AreaService> {create(retrofit = get())}

    single <JIngredientRepository> { JIngredientRepositoryImpl(ingredientService = get())}
    single <IngredientService> { create(retrofit = get())}
}