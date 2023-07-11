package com.example.nutritiontracker20.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.nutritiontracker20.data.models.domain.JMeal

interface MealContract {
    interface ViewModel {

        val meals: LiveData<List<JMeal>>
        val JMeal: LiveData<JMeal>
        fun getMeals()
        fun getMealById(id: String)


        //promene potrebne ali neka stoji ovako chosenCategory da se ne bi crvenilo
        val chosenCategory: LiveData<Any>
        fun search(searchBy: String) // moze da se odlozi pozivanje iz baze na nekoliko sekundi za ovaj search!
        fun setKategorija(/*kategorija: Kategorija?? jer imamo INFO i ListView za tu kategoriju*/)
        fun searchMeal(searchBy: String)
        fun searchCategory(searchBy:String)
        // TODO rad sa podacima u view modelu
        // koja je razlika izmedju rx i obicnog ovog view modela?
    }
}
