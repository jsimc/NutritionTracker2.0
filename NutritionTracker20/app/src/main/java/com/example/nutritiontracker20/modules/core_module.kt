package com.example.nutritiontracker20.modules

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.RawContacts.Data
import androidx.room.Room
import com.example.nutritiontracker20.data.db.MealDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
//    factory<SharedPreferences>{
    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }

    single { Room.databaseBuilder(androidContext(), MealDatabase::class.java,"MealDB")
        .fallbackToDestructiveMigration()
        .build() }
//    single { Room.databaseBuilder(androidContext(), MealDatabase::class.java, name "MealDB")
//        .fallbackToDestructiveMigration()
//        .build() }

    //TODO pogledaj vezbe9 zato sto imaju funkcije createMoshi() i createOkHttpClient() koje se malo razlikuju
    //single<Moshi> { Moshi.Builder().build() }

    single { crateMoshi() }

    single<OkHttpClient> { OkHttpClient.Builder().build() }

}

fun crateMoshi(): Moshi {
    return Moshi.Builder()
        .add(Data::class.java, Rfc3339DateJsonAdapter())
        .build()
}
