package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.daos.SavedMealDao
import com.example.nutritiontracker20.data.datasources.remote.MealService
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.domain.JMeal

class MealRepositoryImpl(
    private val savedMealDao: SavedMealDao,
    private val mealService: MealService
) : MealRepository {
    override fun insertMeal(savedMealsEntity: SavedMealsEntity): Completable {
        return savedMealDao.insertMeal(savedMealsEntity)
    }

    override fun getAll(): Observable<List<SavedMealsEntity>> {
        return  savedMealDao.getAll()
    }

    override fun getById(id: Long): SavedMealsEntity {
        return savedMealDao.getById(id)
    }

    /////////////////////////// sa APIja ////////////////////////////////////////
    override fun getMeals(): Observable<List<Meal>> {
        return mealService
            .getMeals()
            .map {
                it.allMeals.map { jMeal: JMeal ->
                    Meal(
                        idMeal = jMeal.idMeal!!,
                        strMeal = jMeal.strMeal!!,
                        strCategory =  jMeal.strCategory!!,
                        strArea = jMeal.strArea!!,
                        strInstructions = jMeal.strInstructions!!,
                        strMealThumb = jMeal.strMealThumb!!,
                        strTags = jMeal.strTags ?: "",
                        strYoutube = jMeal.strYoutube!!
                    )


                }

            }
    }

    override fun getMealById(id: Int): Observable<Meal> {
        return mealService
            .getMealById(id)
            .map { it.allMeals.map { jMeal ->
                //TODO trebalo bi ingredients staviti u jednu listu
                val strIngredients = mutableListOf<String?>(
                    jMeal.strIngredient1, jMeal.strIngredient2, jMeal.strIngredient3, jMeal.strIngredient4, jMeal.strIngredient5,
                    jMeal.strIngredient6, jMeal.strIngredient7, jMeal.strIngredient8, jMeal.strIngredient9, jMeal.strIngredient10,
                    jMeal.strIngredient11, jMeal.strIngredient12, jMeal.strIngredient13, jMeal.strIngredient14, jMeal.strIngredient15,
                    jMeal.strIngredient16, jMeal.strIngredient17, jMeal.strIngredient18, jMeal.strIngredient19, jMeal.strIngredient20
                )
                val strMeasures = mutableListOf<String?>(
                    jMeal.strMeasure1, jMeal.strMeasure2, jMeal.strMeasure3, jMeal.strMeasure4, jMeal.strMeasure5,
                    jMeal.strMeasure6, jMeal.strMeasure7, jMeal.strMeasure8, jMeal.strMeasure9, jMeal.strMeasure10,
                    jMeal.strMeasure11, jMeal.strMeasure12, jMeal.strMeasure13, jMeal.strMeasure14, jMeal.strMeasure15,
                    jMeal.strMeasure16, jMeal.strMeasure17, jMeal.strMeasure18, jMeal.strMeasure19, jMeal.strMeasure20
                )


                /**
                 *  Pokusala sam prvo da mi ovo ingredients bude lista Ingredients, ali ne znam kako kcal da izracunam.
                 */
//                val ingredients = mutableListOf<String>()
//                val measures = mutableListOf<String>()

                val ingredientsMap = mutableMapOf<String, String>()

                for (i in 0..19) {
                    if(strIngredients[i] == null || strMeasures[i] == null
                        || strIngredients[i] == "" || strMeasures[i] == "") {
                        break
                    }
                    ingredientsMap[strIngredients[i]!!] = strMeasures[i]!!
//                    ingredients.add(Ingredient(strIngredients[i]!!, strMeasures[i]!!.toInt()))
                }

                println(ingredientsMap)

                    Meal(
                        idMeal = jMeal.idMeal!!,
                        strMeal = jMeal.strMeal!!,
                        strCategory =  jMeal.strCategory!!,
                        strArea = jMeal.strArea!!,
                        strInstructions = jMeal.strInstructions!!,
                        strMealThumb = jMeal.strMealThumb!!,
                        strTags = jMeal.strTags ?: "",
                        strYoutube = jMeal.strYoutube!!,
                        ingredients = ingredientsMap
                    )
                }.first()
            }
    }

    override fun getMealByName(name: String): Observable<Meal> {
        return mealService
            .getMealByName(name)
            .map{jMeal->
                Meal(
                    idMeal = jMeal.idMeal!!,
                    strMeal = jMeal.strMeal!!,
                    strCategory =  jMeal.strCategory!!,
                    strArea = jMeal.strArea!!,
                    strInstructions = jMeal.strInstructions!!,
                    strMealThumb = jMeal.strMealThumb!!,
                    strTags = jMeal.strTags ?: "",
                    strYoutube = jMeal.strYoutube!!

                )

            }

    }

    override fun getMealsByFirstLetter(letter: Char): Observable<List<Meal>> {
        return mealService
            .getMealsByFirstLetter(letter)
            .map{
                it.allMeals.map { jMeal ->
                    Meal(
                        idMeal = jMeal.idMeal!!,
                        strMeal = jMeal.strMeal!!,
                        strCategory = jMeal.strCategory!!,
                        strArea = jMeal.strArea!!,
                        strInstructions = jMeal.strInstructions!!,
                        strMealThumb = jMeal.strMealThumb!!,
                        strTags = jMeal.strTags ?: "",
                        strYoutube = jMeal.strYoutube!!
                    )
                }
            }
    }

    override fun filterMealsByCategory(category: String): Observable<List<Meal>> {
        return mealService
            .filterMealsByCategory(category)
            .map{
                it.allMeals.map {jMeal->
                    Meal(
                        idMeal = jMeal.idMeal,
                        strMeal = jMeal.strMeal,
                        strMealThumb = jMeal.strMealThumb,
                    )

                }
            }
    }

    override fun filterMealsByArea(area: String): Observable<List<Meal>> {
        return mealService
            .filterMealsByArea(area)
            .map{
                it.allMeals.map{jMeal->
                    Meal(
                        idMeal = jMeal.idMeal!!,
                        strMeal = jMeal.strMeal!!,
                        strMealThumb = jMeal.strMealThumb!!
                    )

                }
            }
    }

    override fun filterMealsByIngredient(ingredient: String): Observable<List<Meal>> {
        return mealService
            .filterMealsByIngredient(ingredient)
            .map{
                it.allMeals.map{jMeal->
                    Meal(
                        idMeal = jMeal.idMeal!!,
                        strMeal = jMeal.strMeal!!,
                        strMealThumb = jMeal.strMealThumb!!
                    )

                }
            }
    }
}