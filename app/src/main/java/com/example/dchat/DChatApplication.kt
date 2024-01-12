package com.example.dchat

import android.app.Application
import com.example.dchat.db.AppDatabase
import com.example.dchat.repositories.ChatsRepository
import com.example.dchat.db.entities.Chat
import com.example.dchat.db.entities.Message
import com.example.dchat.ui.ChatsListFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

// class InjectedParam()
// class InjectedClass(val subclass: InjectedParam)

val appModule = module {
    // single { InjectedParam() }
    // single { InjectedClass(get()) }

    // TODO: 1 oder 2?
    // Vermutung: 2, weil sonst nur das Companion object eine Singleton Instanz wird
    //single { AppDatabase }
    single { AppDatabase.getInstance(DChatApplication()) }

    viewModel { ChatViewModel(ChatsRepository(get())) }
    factory { Message(get(), get(), get(), get(), get(), get()) }
    factory { Chat(get(), get(), get()) }

    single { ChatsListFragment() }
}


class DChatApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Room database
        // to ensure a single instance through the app's lifecycle
        // HINT: not necessary, because koin makes sure there is only a single instance
        // val database = get<AppDatabase>()

        // Initialize DI with Koin
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android Context
            androidContext(this@DChatApplication)
            modules(appModule)

            // use properties from assets/koin.properties
            // androidFileProperties()
        }

        // Initialize Navigation Compose
        //setupNavigation()
    }

}