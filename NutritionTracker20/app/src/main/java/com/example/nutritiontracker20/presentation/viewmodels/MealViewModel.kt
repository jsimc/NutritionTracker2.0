package com.example.nutritiontracker20.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.entities.SavedMealsEntityWithCount
import com.example.nutritiontracker20.data.models.Category
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.data.models.domain.JIngredient
import com.example.nutritiontracker20.data.models.states.CategoriesState
import com.example.nutritiontracker20.data.repositories.JAreaRepository
import com.example.nutritiontracker20.data.repositories.JCategoryRepository
import com.example.nutritiontracker20.data.repositories.JIngredientRepository
import com.example.nutritiontracker20.data.repositories.MealRepository
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.AREA
import com.example.nutritiontracker20.utils.CATEGORY
import com.example.nutritiontracker20.utils.INGREDIENT
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MealViewModel(
    private val mealRepository: MealRepository,
    private val jCategoryRepository: JCategoryRepository,
    private val jAreaRepository: JAreaRepository,
    private val jIngredientRepository: JIngredientRepository
) : ViewModel(), MealContract.ViewModel {
    private val subscriptions = CompositeDisposable()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    // prati sta se kliknulo na homePage-u, da li Kategorija, Area ili Ingredient. Na osnovu toga se odradjuje MealsPage.
    override val chosenTopAppBar: MutableLiveData<String> = MutableLiveData()

    //meals
    override val mealsState: MutableLiveData<Resource<List<Meal>>> = MutableLiveData()
    override val chosenMeal: MutableLiveData<Resource<Meal>> = MutableLiveData()

    //categories
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
    override val chosenCategory: MutableLiveData<Resource<Category>> = MutableLiveData()

    //areas
    override val areasState: MutableLiveData<Resource<List<String>>> = MutableLiveData()
    override val chosenArea: MutableLiveData<Resource<String>> = MutableLiveData()

    //ingredients !! malo mi se ne slaze logicki,
    // jer sad cu da koristim JIngredient jer imamo ingredient u BAZI koji nema istu strukturu kao ovaj sa APIja koji koristimo!
    override val ingredientsState: MutableLiveData<Resource<List<JIngredient>>> = MutableLiveData()
    override val chosenIngredient: MutableLiveData<Resource<JIngredient>> = MutableLiveData()

    // saved meal state
    override val savedMealState: MutableLiveData<Boolean> = MutableLiveData()

    override val favoriteMealsState: MutableLiveData<Resource<List<SavedMealsEntityWithCount>>> = MutableLiveData()

    init {
        getCategories()
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { mealName ->
                mealRepository
                    .getMealByName(mealName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .onErrorReturnItem(emptyList())
            }
            .subscribe(
                {
                    if (it.isEmpty()) {
                        mealsState.value = Resource.Error(Throwable("No resutls"), listOf())
                    } else {
                        mealsState.value = Resource.Success(it)
                    }
                },
                {
                    mealsState.value = Resource.Error(it, listOf())
                }
            )
        subscriptions.add(subscription)
    }

    override fun getCategories() {
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

    override fun getAreas() {
        val sub = jAreaRepository
            .getAllAreas()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    areasState.value = Resource.Success(it)
                    Log.d("Areas", "GOOD $it")
                },
                {
                    areasState.value = Resource.Error(it, null)
                    Log.d("Areas", "WRONG $it")
                }
            )
        // za dobavljanje Oblasti
        subscriptions.add(sub)
    }

    override fun getIngredients() {
        val sub = jIngredientRepository
            .getAllIngredients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    ingredientsState.value = Resource.Success(it)
                    Log.d("Ingredients", "GOOD $it")
                },
                {
                    ingredientsState.value = Resource.Error(it, null)
                    Log.d("Ingredients", "WRONG $it")
                }
            )
        // za dobavljanje Sastojaka
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
        val sub = mealRepository
            .filterMealsByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealList ->
                    mealsState.value = Resource.Success(mealList)
                    Log.d("Meals", "CATEGORY - GOOD MEAL $mealList")
                },
                {
                    mealsState.value = Resource.Error(it,listOf())
                    Log.d("Meals", "CATEGORY - WRONG MEAL $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    override fun filterMealsByArea(area: String) {
        val sub = mealRepository
            .filterMealsByArea(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealList ->
                    mealsState.value = Resource.Success(mealList)
                    Log.d("Meals", "AREA - GOOD MEAL $mealList")
                },
                {
                    mealsState.value = Resource.Error(it,listOf())
                    Log.d("Meals", "AREA - WRONG MEAL $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    override fun filterMealsByIngredient(ingredient: String) {
        val sub = mealRepository
            .filterMealsByIngredient(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mealList ->
                    mealsState.value = Resource.Success(mealList)
                    Log.d("Meals", "INGREDIENT - GOOD MEAL $mealList")
                },
                {
                    mealsState.value = Resource.Error(it,listOf())
                    Log.d("Meals", "INGREDIENT - WRONG MEAL $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    //TODO MOZDA DA SKLONIMO OVO IPAK
    override fun filterMealsByTags(tags: List<String>) {
        if(mealsState.value is Resource.Success) {
            if(tags.isEmpty()) {
                //topAppBarState
                when(chosenTopAppBar.value) {
                    CATEGORY -> {
                        filterMealsByCategory((chosenCategory.value as Resource.Success).data.strCategory)
                    }
                    AREA -> {
                        filterMealsByArea((chosenArea.value as Resource.Success).data)
                    }
                    INGREDIENT -> {
                        filterMealsByIngredient((chosenIngredient.value as Resource.Success).data.strIngredient!!)
                    }
                    else -> {
                        Log.d("filterMealsByTags", "Error")
                    }
                }
            } else {
                Log.d("filterMealsByTags", "tags: $tags")
                mealsState.value = Resource.Success((mealsState.value as Resource.Success).data
                    .filter {
                        // ZATO STO JE API SR**E, MEAL TAGS CE UVEK BITI PRAZNA LISTA....
//                        val mealTags = it.strTags.split(", ", ",") // Nadajmo se da ovaj split vraca dobru listu,
                        // API JE UZASAN I NEMA TAGOVE U OVIM JELIMA, TAKO DA CEMO DA GLEDAMO PO IMENU BMK
//                        Log.d("filterMealsByTags", "meal: ${it.strMeal}, meal.strTags: ${it.strTags}, mealTags: $mealTags")
//                        mealTags.containsAll(tags)// a onda proveravamo da li tagovi od datog jela sadrze sve tagove koje sam prosledila.

                        // tags nikad nece biti empty u else blocku
                        it.strMeal.contains(tags[0], true)
                    }
                )
            }
        }
    }

    override fun search(searchBy: String) {
        TODO("Not yet implemented")
//        print(searchBy)
        //DEBOUNCE for waiting

    }

    override fun searchMeal(searchBy: String) {
        publishSubject.onNext(searchBy)
    }

    override fun searchCategory(searchBy: String) {
        TODO("Not yet implemented")
    }

    override fun setKategorija(category: Category) {
        chosenCategory.value = Resource.Success(category)
        chosenTopAppBar.value = CATEGORY
    }

    override fun setArea(area: String) {
        chosenArea.value = Resource.Success(area)
        chosenTopAppBar.value = AREA
    }

    override fun setIngredient(ingredient: JIngredient) {
        chosenIngredient.value = Resource.Success(ingredient)
        chosenTopAppBar.value = INGREDIENT
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun saveMealToFavorites(savedMealsEntity: SavedMealsEntity) {
        val sub = mealRepository
            .insertMeal(savedMealsEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    savedMealState.value = true
                    Log.d("SaveMeal", "SAVED")
                },
                {
                    savedMealState.value = false
                    Log.d("SaveMeal", "NOT SAVED: $it")
                }
            )
        // za dobalvjanje kategorija, Oblasti. .. .
        subscriptions.add(sub)
    }

    override fun getAllCountDescByName() {
        val sub = mealRepository
            .getAllCountDescByName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favoriteMealsState.value = Resource.Success(it)
                    Log.d("FavoriteMeals", "Success-Name")
                },
                {
                    favoriteMealsState.value = Resource.Error(it, listOf())
                    Log.d("FavoriteMeals", "Error-Name")
                }
            )
        subscriptions.add(sub)
    }

    override fun getAllCountDescByCategory() {
        val sub = mealRepository
            .getAllCountDescByCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favoriteMealsState.value = Resource.Success(it)
                    Log.d("FavoriteMeals", "Success-Category")
                },
                {
                    favoriteMealsState.value = Resource.Error(it, listOf())
                    Log.d("FavoriteMeals", "Error-Category")
                }
            )
        subscriptions.add(sub)    }

    override fun getAllCountDescByArea() {
        val sub = mealRepository
            .getAllCountDescByArea()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favoriteMealsState.value = Resource.Success(it)
                    Log.d("FavoriteMeals", "Success-Area")
                },
                {
                    favoriteMealsState.value = Resource.Error(it, listOf())
                    Log.d("FavoriteMeals", "Error-Area")
                }
            )
        subscriptions.add(sub)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}