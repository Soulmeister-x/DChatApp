package com.example.dchat.data

import android.content.Context
import com.example.dchat.data.repositories.ChatsRepository
import com.example.dchat.data.repositories.impl.ChatsRepositoryImpl

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val chatsRepository: ChatsRepository
}

/**
 * [AppContainer] implementation that provides instance of [ChatsRepositoryImpl].
 */
class AppDataContainer(private val context: Context) : AppContainer {

    private val appDb = AppDatabase.getInstance(context)
    /**
     * Implementation for [ChatsRepository].
     */
    override val chatsRepository: ChatsRepository by lazy {
        ChatsRepositoryImpl(
            appDb.messageDao(),
            appDb.chatDao()
        )
    }
}