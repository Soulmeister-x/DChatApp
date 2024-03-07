package com.example.dchat

import android.app.Application
import com.example.dchat.di.appModule
import com.example.dchat.di.networkModule
import com.example.dchat.network.pds.pdsModule
import com.example.dchat.ui.pdsViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger()
            modules(
                appModule,
                networkModule,
                pdsModule,
                pdsViewModelModule,
            )
        }
    }
}
