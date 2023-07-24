package com.example.nutritiontracker20.data.repositories

import com.example.nutritiontracker20.data.models.Category
import io.reactivex.Observable

interface JCategoryRepository {
    fun getAllCategories(): Observable<List<Category>>
}