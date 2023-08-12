package com.example.nutritiontracker20.data.repositories

import io.reactivex.Observable

interface JAreaRepository {
    fun getAllAreas(): Observable<List<String>>
}