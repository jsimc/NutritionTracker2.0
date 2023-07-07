package com.example.nutritiontracker20.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.example.nutritiontracker20.modules.coreModule
import com.example.nutritiontracker20.modules.mealModule

class NutritionTracker20Application : Application(){
    override fun onCreate() {
        super.onCreate()
//        AppCompatDelegate.setDefaultNightMode(
//            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        init()
    }

    private fun init() {
        initTimber()
        initKoin()

    }

    private fun initTimber() {
//        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            mealModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@NutritionTracker20Application)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            //fragmentFactory()
            // modules
            modules(modules)
        }
    }
}