package com.example.dchat.data

import android.content.Context
import com.example.dchat.data.repositories.ChatsRepository
import com.example.dchat.data.repositories.impl.ChatsRepositoryImpl
import kotlinx.coroutines.currentCoroutineContext

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
    /**
     * Implementation for [ChatsRepository].
     */
    override val chatsRepository: ChatsRepository by lazy {
        ChatsRepositoryImpl(AppDatabase.getInstance(context).chatDao())
    }
}