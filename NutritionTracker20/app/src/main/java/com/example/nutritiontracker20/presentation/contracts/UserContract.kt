package com.example.nutritiontracker20.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.nutritiontracker20.data.models.User
import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender

interface UserContract {
    interface ViewModel {
        val isLoggedIn: LiveData<Boolean>
        val loggedUser: LiveData<User>
//        val username: LiveData<String>

        // hocemo li ostale informacije o korisniku da vadimo iz baze ili isto da ih imamo ovako u view modelu?
        fun checkForUser(username:String, password: String): Boolean
//        suspend fun setLoggedIn(b: Boolean/**, user: User*/)

        fun updateInfo(username: String, password: String, age: Int, height: Int, weight: Int, gender: eGender, weeklyActivity: eActivity)

        // set funkcije su samo da se postavi na viewmodel
        fun updateAge(username: String, age: Int)
        fun updateHeight(username: String, height: Int)
        fun updateWeight(username: String, weight: Int)
        fun updateGender(username: String, gender: eGender)
        fun updateWeeklyActivity(username: String, weeklyActivity: eActivity)
        fun updateKcal(username: String, suggestedKcal: Int)

        fun getAge(username: String): Int
    }
}
