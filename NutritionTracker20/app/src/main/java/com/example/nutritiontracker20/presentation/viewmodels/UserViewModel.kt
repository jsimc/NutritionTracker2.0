package com.example.nutritiontracker20.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritiontracker20.data.helpers.userEntityToUser
import com.example.nutritiontracker20.data.helpers.userToUserEntity
import com.example.nutritiontracker20.data.models.User
import com.example.nutritiontracker20.data.repositories.UserRepository
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository
) : ViewModel(), UserContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override var loggedUser: MutableLiveData<User> = MutableLiveData()
    override var suggestedKcal: MutableLiveData<Int> = MutableLiveData(0)
    override var flagIsLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        viewModelScope.launch {
            println("INIT BLOK USERVIEWMODEL")
            println("Logged user1: ${loggedUser.value}")
            val sub = userRepository
                .getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { it ->
                    loggedUser.value = it.map {
                        userEntityToUser(it)
                    }[0]
                    tmp.value = loggedUser.value
                    println("Logged user2: ${loggedUser.value}")
                },
                {

                })
            subscriptions.add(sub)
        }

    }

    override val tmp: MutableLiveData<User> = MutableLiveData()
    override fun changeTmp(newTmp: User) {
        tmp.value = newTmp
    }

    //check for user: potrazi u bazi usera sa datim imenom, ako ga ima odmah postavlja da je loggedUser taj
    // i trebalo bi da obavesti view -> tj login screen da je uspesno ulogovan i da moze da se prebaci na homepage
    override fun checkForUser(username: String, password: String) {
        println("USERNAMMME: $username, $password")
        val sub = userRepository
            .getUser(username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println("FLAG: true")
                    flagIsLoggedIn.value = true
//                    loggedUser.value = userEntityToUser(it)
                },
                {
                    println("ISLOGGEDIN = FALSE")
                }
            )
        subscriptions.add(sub)
    }


    override fun setUser(user: User) {
        val sub = userRepository
            .getUser(user.username)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loggedUser.value = user //userEntityToUser(it)
//                    suggestedKcal.value = it.suggestedKcal ?: suggestedKcal.value
                    println("COMPLETED - GET USER - ${loggedUser.value}")
                },
                {

                }
            )
        subscriptions.add(sub)
    }

    override fun insertUser(user: User) {
        val sub = userRepository.insert(userToUserEntity(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    println("COMPLETED - ADDED USER - $user")
                },
                {
                    println("NOT COMPLETED - NOT ADDED USER")
                }
            )
        subscriptions.add(sub)
    }

    override fun changeUser(user: User) {
        loggedUser.value = user
    }

    override fun updateUser(user: User) {
        val sub = userRepository
            .update(userToUserEntity(user))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { /* updated! */
                    setUser(user)
                    println("updated")},
                { /* not updated! */ println("not updated")}
            )
        subscriptions.add(sub)
    }
////////////////////////////////////////////////////////////////////////
    override fun updateInfo(username: String, password: String, age: Int, height: Int, weight: Int, gender: eGender, weeklyActivity: eActivity) {
        updatePassword(username, password)
        updateAge(username, age)
        updateGender(username, gender)
        updateHeight(username, height)
        updateWeight(username, weight)
        updateWeeklyActivity(username, weeklyActivity)
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
