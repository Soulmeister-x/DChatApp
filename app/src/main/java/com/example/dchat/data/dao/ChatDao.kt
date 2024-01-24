package com.example.dchat.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dchat.data.entities.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): Flow<List<Chat>>

    @Query("SELECT * FROM chats WHERE id = :chatId")
    fun getChatById(chatId: Int): Flow<Chat>

    @Upsert
    suspend fun insertChat(chat: Chat)

    @Query("DELETE FROM chats")
    suspend fun deleteAllChats()

    @Query("DELETE FROM chats WHERE id = :chatId")
    suspend fun deleteChatById(chatId: Int)
}