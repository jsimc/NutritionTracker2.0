package com.example.nutritiontracker20.data.repositories

import com.example.nutritiontracker20.data.datasources.remote.CategoryService
import com.example.nutritiontracker20.data.models.Category
import io.reactivex.Observable

class JCategoryRepositoryImpl(private val categoryService: CategoryService) : JCategoryRepository{
    override fun getAllCategories(): Observable<List<Category>> {
        return categoryService
            .getAllCategories()
            .map {
                it.allCategories.map { jCategory ->
                    Category (
                        idCategory = jCategory.idCategory,
                        strCategory = jCategory.strCategory,
                        strCategoryDescription = jCategory.strCategoryDescription,
                        strCategoryThumb = jCategory.strCategoryThumb
                    )
                }
            }
    }
}