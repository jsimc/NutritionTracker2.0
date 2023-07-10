package com.example.nutritiontracker20.modules

import com.example.nutritiontracker20.data.datasources.daos.UserDao
import com.example.nutritiontracker20.data.db.MealDatabase
import com.example.nutritiontracker20.data.repositories.UserRepository
import com.example.nutritiontracker20.data.repositories.UserRepositoryImpl
import com.example.nutritiontracker20.presentation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { UserViewModel(userRepository = get()) }

    single<UserRepository> { UserRepositoryImpl(userDao = get())}
    single<UserDao> { get<MealDatabase>().getUserDao()}

//    single<UserLoginState> { UserLoginState(get()) }
}