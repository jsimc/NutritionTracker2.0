package com.example.nutritiontracker20.data.datasources.remote

import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.domain.JIngredient
import retrofit2.http.GET

interface IngredientService {

    @GET("list.php?i=list")
    fun getAllIngredients(): Observable<List<JIngredient>>

    //TODO onaj drugi api
}