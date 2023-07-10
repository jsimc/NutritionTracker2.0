package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.UserEntity
import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender

interface UserRepository {
    fun getUser(username: String): Observable<UserEntity>
    fun insert(userEntity: UserEntity): Completable
    fun getAll(): Observable<List<UserEntity>>
    fun update(userEntity: UserEntity) : Completable
    fun updatePassword(username: String, password: String): Completable
    fun updateAge(username: String, age: Int): Completable
    fun updateHeight(username: String, height: Int): Completable
    fun updateWeight(username: String, weight: Int): Completable
    fun updateGender(username: String, gender: eGender): Completable
    fun updateWeeklyActivity(username: String, weeklyActivity: eActivity): Completable
    fun updateKcal(username: String, suggestedKcal: Int): Completable
    fun delete(username: String): Completable
}