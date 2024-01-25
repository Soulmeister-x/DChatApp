package com.example.dchat.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.dchat.ChatViewModel
import org.koin.core.module.dsl.singleOf
import com.example.dchat.data.repositories.ChatsRepository
import com.example.dchat.data.repositories.ChatsRepositoryImpl
import org.koin.dsl.bind
import com.example.dchat.data.AppDataContainer

val appModule = module {
    viewModelOf(::ChatViewModel)

    singleOf(::AppDataContainer)
    singleOf(::ChatsRepositoryImpl) bind ChatsRepository::class
}