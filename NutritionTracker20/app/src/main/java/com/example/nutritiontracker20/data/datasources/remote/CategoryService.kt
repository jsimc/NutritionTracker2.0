package com.example.nutritiontracker20.data.datasources.remote

import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.domain.JCategory
import retrofit2.http.GET

interface CategoryService {

    @GET("categories.php")
    fun getAllCategories(): Observable<List<JCategory>>

//mislim da nam ni ne treba ovaj
    @GET("list.php?c=list")
    fun getAllCategoryNames(): Observable<List<String>>

}