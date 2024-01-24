package com.example.dchat

import android.app.Application
import android.util.Log


class DChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Log.i("log", "DChatApplication onCreate")

        // Initialize Room database
        //val database = AppDatabase.getInstance(this@DChatApplication)
        // database.insertMockData(mockData)

        // Initialize Navigation Compose
        //setupNavigation()
    }

}