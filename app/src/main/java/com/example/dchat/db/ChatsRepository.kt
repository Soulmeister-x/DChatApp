package com.example.dchat.db

import com.example.dchat.db.dao.ChatDao
import com.example.dchat.db.entities.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatsRepository(private val chatDao: ChatDao) {

/*
    suspend fun getAllChats(): Flow<List<Chat>> = flow {
        emit(chatDao.getAllChats())
    }

 */
    fun getAllChats(): List<Chat> = chatDao.getAllChats()

    suspend fun findChatById(chatId: Int): Flow<Chat> = flow {
        emit(chatDao.findChatById(chatId))
    }

    suspend fun insertChat(chat: Chat) =
        chatDao.insertChat(chat)

    suspend fun insertAllChats(chats: List<Chat>) =
        chatDao.insertAllChats(chats)

    suspend fun delete(chatId: Int) =
        chatDao.delete(chatId)
}