package com.example.nutritiontracker20.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontracker20.data.models.Category
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.data.models.states.CategoriesState
import com.example.nutritiontracker20.data.repositories.JCategoryRepository
import com.example.nutritiontracker20.data.repositories.MealRepository
import com.example.nutritiontracker20.presentation.contracts.MealContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MealViewModel(
    private val mealRepository: MealRepository,
    private val jCategoryRepository: JCategoryRepository
) : ViewModel(), MealContract.ViewModel {
    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    //meals
    override val mealsState: MutableLiveData<Resource<List<Meal>>> = MutableLiveData()
    override val chosenMeal: MutableLiveData<Resource<Meal>> = MutableLiveData()

    //categories
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
    override val chosenCategory: MutableLiveData<Category> = MutableLiveData()

    init {

        Log.d("mealViewModel", "INIT")
        val sub = jCategoryRepository
            .getAllCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    categoriesState.value = CategoriesState.Success(it)
                    Log.d("Categories", "GOOD $it")
                },
                {
                    categoriesState.value = CategoriesState.Error("Error happened while fetching data from db")
                    Log.d("Categories", "WRONG $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    override fun getMeals() {
    }

    override fun getMealById(id: Int) {
        val sub = mealRepository
            .getMealById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    chosenMeal.value = Resource.Success(it)
                    Log.d("Meals", "GOOD $it")
                },
                {
                    chosenMeal.value = Resource.Error(it, null)
                    Log.d("Meals", "WRONG $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    override fun getMealByName(name: String) {
        TODO("Not yet implemented")
    }

    override fun getMealsByFirstLetter(letter: Char) {
        TODO("Not yet implemented")
    }

    override fun filterMealsByCategory(category: String) {
        println("CATEGORY IN FILTER MEALS BY CAT: $category")
        val sub = mealRepository
            .filterMealsByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealList ->
                    mealsState.value = Resource.Success(mealList)
                    Log.d("Meals", "GOOD MEAL $mealList")
                },
                {
                    mealsState.value = Resource.Error(it,listOf())
                    Log.d("Meals", "WRONG MEAL $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    override fun filterMealsByArea(area: String) {
        TODO("Not yet implemented")
    }

    override fun filterMealsByIngredient(ingredient: String) {
        TODO("Not yet implemented")
    }

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

    override fun setKategorija(category: Category) {
        chosenCategory.value = category
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}