package com.example.nutritiontracker20.data.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.nutritiontracker20.data.models.domain.JIngredient
import com.example.nutritiontracker20.data.repositories.JIngredientRepository

class IngredientPagingSource(
    val jIngredientRepository: JIngredientRepository
): PagingSource<Int, JIngredient>() {

    override fun getRefreshKey(state: PagingState<Int, JIngredient>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JIngredient> {
//        try {
//            // Start refresh at page 1 if undefined.
//            val nextPageNumber = params.key ?: 1
//            val response = jIngredientRepository.getAllIngredients(query, nextPageNumber)
//            return LoadResult.Page(
//                data = response.users,
//                prevKey = null, // Only paging forward.
//                nextKey = response.nextPageNumber
//            )
//        } catch (e: Exception) {
//            // Handle errors in this block and return LoadResult.Error for
//            // expected errors (such as a network failure).
//        }
        TODO("Not yet implemented")
    }
}