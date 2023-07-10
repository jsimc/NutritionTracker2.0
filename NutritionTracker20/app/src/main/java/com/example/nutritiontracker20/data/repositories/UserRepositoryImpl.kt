package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.daos.UserDao
import com.example.nutritiontracker20.data.entities.UserEntity
import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    override fun getUser(username: String): Observable<UserEntity> {
        return Observable.fromCallable {
            userDao.getByUsername(username)
        }
    }

    override fun insert(userEntity: UserEntity): Completable {
        return userDao.insertUser(userEntity)
    }

    override fun getAll(): Observable<List<UserEntity>> {
        return userDao.getAll()
    }

    override fun update(userEntity: UserEntity): Completable {
        return Completable.fromCallable{
            userDao.update(userEntity)
        }
        TODO("nmp kako ovo da uradim isk al mozda i ne treba??")
        // mozda da update bude kao za sve ovo? nzm
    }

    override fun updatePassword(username: String, password: String): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                password = password
            )
            userDao.update(updatedUser)
        }
    }

    override fun updateAge(username: String, age: Int): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                age = age
            )
            userDao.update(updatedUser)
        }    }

    override fun updateHeight(username: String, height: Int): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                height = height
            )
            userDao.update(updatedUser)
        }    }

    override fun updateWeight(username: String, weight: Int): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                weight = weight
            )
            userDao.update(updatedUser)
        }    }

    override fun updateGender(username: String, gender: eGender): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                gender = gender
            )
            userDao.update(updatedUser)
        }    }

    override fun updateWeeklyActivity(username: String, weeklyActivity: eActivity): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                weeklyActivity = weeklyActivity
            )
            userDao.update(updatedUser)
        }    }

    override fun updateKcal(username: String, suggestedKcal: Int): Completable {
        return  Completable.fromCallable {
            val user = userDao.getByUsername(username)
            val updatedUser = user.copy(
                suggestedKcal = suggestedKcal
            )
            userDao.update(updatedUser)
        }    }
    override fun delete(username:String): Completable {
        val tmpUser = userDao.getByUsername(username)
        //return userDao.delete(tmpUser)
        TODO("ne znam kako delete da uradim oops")
    }
}