package com.example.dchat.db

import com.example.dchat.db.dao.ChatDao
import com.example.dchat.db.entities.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.java.KoinJavaComponent.inject

interface DataRepository {
    fun getAllChats(): Flow<List<Chat>>
}



class ChatsRepository constructor(
    database: AppDatabase
): DataRepository {

    val chatDao = database.chatDao()

/*
    suspend fun getAllChats(): Flow<List<Chat>> = flow {
        emit(chatDao.getAllChats())
    }

 */
    override fun getAllChats(): Flow<List<Chat>> = chatDao.getAllChats()

    suspend fun findChatById(chatId: Int): Flow<Chat> = flow {
        emit(chatDao.getChatById(chatId))
    }

    suspend fun insertChat(chat: Chat) =
        chatDao.insertChat(chat)

    suspend fun insertAllChats(chats: List<Chat>) =
        chatDao.insertAllChats(chats)

    suspend fun delete(chatId: Int) =
        chatDao.delete(chatId)
}