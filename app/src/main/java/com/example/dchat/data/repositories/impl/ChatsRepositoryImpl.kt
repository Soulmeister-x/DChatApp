package com.example.dchat.data.repositories.impl

import android.util.Log
import com.example.dchat.data.dao.ChatDao
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow

/**
 * The repository acts as an intermediary between the database and the viewmodel.
 * It handles data retrieval and manipulation, shielding the viewmodel from the complexities of
 * database interactions. The repository provides a clean interface for accessing chat data,
 * allowing the viewmodel to focus on UI updates.
 */
class ChatsRepositoryImpl(private val chatDao: ChatDao): ChatsRepository {

    override fun getAllChats(): Flow<List<Chat>> = chatDao.getAllChats()

    override fun getChatById(chatId: Int): Flow<Chat> =
        chatDao.getChatById(chatId)


    override suspend fun insertChat(chat: Chat) =
        chatDao.insertChat(chat)


    override suspend fun insertAllChats(chats: List<Chat>) {
        Log.i("log", "trying to insert ${chats}")
        chatDao.insertAllChats(chats)
    }
    override suspend fun updateChat(chat: Chat) =
        chatDao.updateChat(chat)

    override suspend fun deleteChat(chat: Chat) =
        chatDao.deleteChat(chat)
}