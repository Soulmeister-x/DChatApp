package com.example.dchat.data

import com.example.dchat.network.ChatsRepository
import com.example.dchat.network.ChatsRepositoryImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * App container for Dependency injection.
 */
interface AppContainer : KoinComponent {
    val appDb: AppDatabase
    val chatsRepository: ChatsRepository
}

/**
 * [AppContainer] implementation that provides instance of [ChatsRepositoryImpl].
 */
class AppDataContainer : AppContainer {

    override val appDb: AppDatabase by inject()

    /**
     * Implementation for [ChatsRepository].
     */
    override val chatsRepository: ChatsRepository by inject()
}