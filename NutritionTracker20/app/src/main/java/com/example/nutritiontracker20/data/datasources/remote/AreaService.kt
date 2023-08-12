package com.example.nutritiontracker20.data.datasources.remote

import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.domain.MealsFromJson
import retrofit2.http.GET

interface AreaService {
    @GET("list.php?a=list")
    fun getAllAreas(): Observable<MealsFromJson>

//    @GET("lookup.php")
//    fun getMealById(@Query("i") id: Int): Observable<MealsFromJson>
}