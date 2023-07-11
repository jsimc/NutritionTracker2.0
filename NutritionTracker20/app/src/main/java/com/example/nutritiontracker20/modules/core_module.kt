package com.example.nutritiontracker20.modules

import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.RawContacts.Data
import androidx.room.Room
import com.example.nutritiontracker20.BuildConfig
import com.example.nutritiontracker20.data.db.MealDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }
    //single znaci da je jedinstveni i uvek se na isti vraca referenca
    single {createMoshi()}
    single {createOkHttpClient()}
    single {createRetrofit(moshi = get(), httpClient = get())}

    single { Room.databaseBuilder(androidContext(), MealDatabase::class.java,"MealDB")
        .fallbackToDestructiveMigration()
        .build() }

    //TODO pogledaj vezbe9 zato sto imaju funkcije createMoshi() i createOkHttpClient() koje se malo razlikuju
    //single<Moshi> { Moshi.Builder().build() }

    //sta je ovo?
    single<OkHttpClient> { OkHttpClient.Builder().build() }

}

//za serijalizaciju jsona
fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(Data::class.java, Rfc3339DateJsonAdapter())
        .build()
}
// ovo ce morati drugacije jer vucemo podatke sa dva apija
fun createRetrofit(moshi: Moshi,
                   httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("www.themealdb.com/api/json/v1/1/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .client(httpClient)
        .build()

}

fun createOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(60, TimeUnit.SECONDS)
    httpClient.connectTimeout(60, TimeUnit.SECONDS)
    httpClient.writeTimeout(60, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    return httpClient.build()
}

