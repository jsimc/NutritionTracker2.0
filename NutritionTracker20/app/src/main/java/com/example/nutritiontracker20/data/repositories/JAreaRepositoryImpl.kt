package com.example.nutritiontracker20.data.repositories

import com.example.nutritiontracker20.data.datasources.remote.AreaService
import io.reactivex.Observable

class JAreaRepositoryImpl(private val areaService: AreaService): JAreaRepository {
    override fun getAllAreas(): Observable<List<String>> {
        return areaService
            .getAllAreas()
            .map {
                //area je svakako samo string, nece imati sliku.
                it.allMeals.map {
                    jMeal -> jMeal.strArea!!
                }
            }
    }
}