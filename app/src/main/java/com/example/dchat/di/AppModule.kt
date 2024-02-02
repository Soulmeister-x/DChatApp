package com.example.dchat.di

import androidx.room.Room
import com.example.dchat.data.AppContainer
import com.example.dchat.data.AppDataContainer
import com.example.dchat.data.AppDatabase
import com.example.dchat.network.ChatsRepository
import com.example.dchat.network.ChatsRepositoryImpl
import com.example.dchat.ui.ChatsUiState
import com.example.dchat.ui.ChatsViewModel
import com.example.dchat.ui.ContactsUiState
import com.example.dchat.ui.ContactsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AppDataContainer) { bind<AppContainer>() }

    single<ChatsRepository> { ChatsRepositoryImpl() }

    single {
        Room.databaseBuilder(
        androidApplication(),
        AppDatabase::class.java,
        "appDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { ChatsUiState(get()) }
    factory { ContactsUiState(get()) }

    viewModel { ChatsViewModel(get()) }
    viewModel { ContactsViewModel(get()) }
}