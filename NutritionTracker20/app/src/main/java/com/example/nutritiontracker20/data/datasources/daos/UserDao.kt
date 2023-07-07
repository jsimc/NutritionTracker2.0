package com.example.nutritiontracker20.data.datasources.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.UserEntity

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertUser(UserEntity: UserEntity): Completable

    //isk nam ne treba al neka
    @Query("SELECT * FROM users")
    abstract fun getAll(): Observable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE username == :username")
    abstract fun getByUsername(username: String): UserEntity

//    @Query("SELECT suggestedKcal FROM users WHERE username == :username")
//    abstract fun getByUsername(username: String): Int

    //ne kapiram bas kako ovaj roomov update radi
    @Transaction
    @Update
    abstract fun update(userEntity: UserEntity)
/*
    @Transaction
    @Query("UPDATE users SET password = :password WHERE username == :username")
    abstract fun updatePassword(username: String, password: String): Completable

    @Transaction
    @Query("UPDATE users SET age = :age WHERE username == :username")
    abstract fun updateAge(username: String, age: Int): Completable

    @Transaction
    @Query("UPDATE users SET height = :height WHERE username == :username")
    abstract fun updateHeight(username: String, height: Int): Completable

    @Transaction
    @Query("UPDATE users SET weight = :weight WHERE username == :username")
    abstract fun updateWeight(username: String, weight: Int): Completable

    @Transaction
    @Query("UPDATE users SET gender = :gender WHERE username == :username")
    abstract fun updateGender(username: String, gender: eGender): Completable

    @Transaction
    @Query("UPDATE users SET weeklyActivity = :weeklyActivity WHERE username == :username")
    abstract fun updateWeeklyActivity(username: String, weeklyActivity: eActivity): Completable

    @Transaction
    @Query("UPDATE users SET suggestedKcal = :suggestedKcal WHERE username == :username")
    abstract fun updateKcal(username: String, suggestedKcal: Int): Completable
  */

    /*
    var username : String,
    var password : String,
    var age : Int,
    var height : Int,
    var weight : Int,
    var gender :gender,
    var weeklyActivity: activity,
    var suggestedKcal : Int,
     */

    //ni ovo ne kapiram bas
    @Delete
    abstract fun delete(userEntity: UserEntity)

//    @Transaction
//    open fun getByUsernameAndUpdate(username: String, password: String){
//        val user = getByUsername(username)
//        val updatedUser = user.copy(
//            password = password
//        )
//        update(updatedUser)
//    }



}