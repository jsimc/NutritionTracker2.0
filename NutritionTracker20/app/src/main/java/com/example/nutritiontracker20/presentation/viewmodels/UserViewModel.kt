package com.example.nutritiontracker20.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontracker20.data.helpers.userToUserEntity
import com.example.nutritiontracker20.data.models.User
import com.example.nutritiontracker20.data.repositories.UserRepository
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(private val userRepository: UserRepository
) : ViewModel(), UserContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)
    override val loggedUser: MutableLiveData<User> = MutableLiveData()

    init {
        println("INIT BLOK USERVIEWMODEL")
    }

    override fun checkForUser(username: String, password: String): Boolean {
        return true
    }

    override fun updateInfo(username: String, password: String, age: Int, height: Int, weight: Int, gender: eGender, weeklyActivity: eActivity) {
        updatePassword(username, password)
        updateAge(username, age)
        updateGender(username, gender)
        updateHeight(username, height)
        updateWeight(username, weight)
        updateWeeklyActivity(username, weeklyActivity)
    }

    fun update() {
        val sub = userRepository
            .update(userToUserEntity(loggedUser.value!!))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                {}
            )
        subscriptions.add(sub)
    }


    private fun updatePassword(username: String, password: String) {
        val subscription = userRepository
            .updatePassword(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateAge(username: String, age: Int) {
        val subscription = userRepository
            .updateAge(username, age)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateHeight(username: String, height: Int) {
        val subscription = userRepository
            .updateHeight(username, height)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }
    override fun updateWeight(username: String, weight: Int) {
        val subscription = userRepository
            .updateWeight(username, weight)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateGender(username: String, gender: eGender) {
        val subscription = userRepository
            .updateGender(username, gender)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateWeeklyActivity(username: String, weeklyActivity: eActivity) {
        val subscription = userRepository
            .updateWeeklyActivity(username, weeklyActivity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateKcal(username: String, suggestedKcal: Int) {
        val subscription = userRepository
            .updateKcal(username, suggestedKcal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // TODO za npr neki UserState? Mozda, ako svakako imamo samo jedno dugme na kraju, da tek tu stavim state.
                    println("Uspelo")
                },
                {
                    println("NEUSPELO")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAge(username: String): Int {
        var age: Int = 0
        val sub = userRepository.getUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { age = it.age?: 0 },
                {}
            )
        subscriptions.add(sub)
        return age
    }

    override fun onCleared() {
        super.onCleared()
        println("CLEARRRRREEEEDDDDD")
        subscriptions.clear()
    }
}
