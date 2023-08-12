package com.example.nutritiontracker20.data.datasources.remote

import androidx.paging.PagingSource
import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.domain.JListIngredients
import retrofit2.http.GET

interface IngredientService {

    @GET("list.php?i=list")
    fun getAllIngredients(): Observable<JListIngredients>


//    www.themealdb.com/api/json/v1/1/filter.php?i=chicken_breast - ovo je za filter po main ingreedientu
    //TODO onaj drugi api
}