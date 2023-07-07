package com.example.nutritiontracker20.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontracker20.data.repositories.MealRepository
import com.example.nutritiontracker20.presentation.contracts.MealContract

class MealViewModel(private val mealRepository: MealRepository) : ViewModel(), MealContract.ViewModel {
    override val chosenCategory: MutableLiveData<Any> = MutableLiveData()

    override fun search(searchBy: String) {
        TODO("Not yet implemented")
//        print(searchBy)
        //DEBOUNCE for waiting

    }

    override fun searchMeal(searchBy: String) {
        TODO("Not yet implemented")
    }

    override fun searchCategory(searchBy: String) {
        TODO("Not yet implemented")
    }

    override fun setKategorija() {
        TODO("Not yet implemented")
    }
}