package com.example.app

import android.app.Application
import com.example.app.data.AppContainer
import com.example.app.data.DefaultAppContainer

/**
 * Bike application
 *
 * @constructor Create empty Bike application
 */
class BikeApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = applicationContext)
    }
}