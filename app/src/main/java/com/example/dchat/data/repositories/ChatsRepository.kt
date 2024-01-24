package com.example.dchat.data.repositories

import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Message
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {
    fun getAllMessagesSortedByChatId(): Flow<List<Message>>
    fun getMessagesByChatId(chatId: Int): Flow<List<Message>>
    fun getMessageById(messageId: Int): Flow<Message>
    fun getChatById(chatId: Int): Flow<Chat>
    suspend fun updateMessages(messages: List<Message>)
    suspend fun insertAllMessages(messages: List<Message>)
    suspend fun deleteChatById(chatId: Int)
    suspend fun deleteMessage(message: Message)
}