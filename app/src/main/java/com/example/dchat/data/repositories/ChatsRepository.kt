package com.example.dchat.data.repositories

import com.example.dchat.ChatDetails
import com.example.dchat.data.entities.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ChatsRepository {
    fun getAllChats(): Flow<List<Chat>>
    fun getChatById(chatId: Int): Flow<Chat>
    suspend fun insertChat(chat: Chat)
    suspend fun updateChat(chat: Chat)
    suspend fun insertAllChats(chats: List<Chat>)
    suspend fun deleteChat(chat: Chat)
}