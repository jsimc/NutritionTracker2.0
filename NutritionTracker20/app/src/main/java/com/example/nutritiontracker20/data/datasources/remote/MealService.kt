package com.example.nutritiontracker20.data.datasources.remote

import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.domain.MealsFromJson
import com.example.nutritiontracker20.data.models.domain.MealsFromJsonFilterByCategory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealService {

    @GET("search.php?s")
    fun getMeals(): Observable<MealsFromJson>
    @GET("lookup.php")
    fun getMealById(@Query("i") id: Int): Observable<MealsFromJson>

    @GET("search.php")
    fun getMealByName(@Query("s") name: String): Observable<MealsFromJson>

    @GET("search.php?f={letter}")
    fun getMealsByFirstLetter(@Path("letter") letter: Char): Observable<MealsFromJson>

    // !!! Mislim da mora sa @Query i na ovakav nacin, meni je bacao problem da nece ovako npr. f = {letter}, vrv zbog injection?
    @GET("filter.php")
    fun filterMealsByCategory(@Query("c") category: String): Observable<MealsFromJsonFilterByCategory>

    @GET("filter.php")
    fun filterMealsByArea(@Query("a") area: String): Observable<MealsFromJson>

    @GET("filter.php")
    fun filterMealsByIngredient(@Query("i") ingredient: String): Observable<MealsFromJson>

}