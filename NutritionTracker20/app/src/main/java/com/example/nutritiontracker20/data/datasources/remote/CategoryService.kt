package com.example.nutritiontracker20.data.datasources.remote

import com.example.nutritiontracker20.data.models.domain.CategoriesFromJson
import io.reactivex.Observable
import retrofit2.http.GET

interface CategoryService {

    // CategoriesFromJson je samo wrapper klasa za Lsit<JCategory> nece drugacije Json da se parsira.
    @GET("categories.php")
    fun getAllCategories(): Observable<CategoriesFromJson>

    //mislim da nam ni ne treba ovaj
    @GET("list.php?c=list")
    fun getAllCategoryNames(): Observable<List<String>>

}