package com.example.dchat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.dchat.data.entities.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY chatId")
    fun getAllMessagesSortedByChatId(): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE id = :chatId")
    fun getMessagesByChatId(chatId: Int): Flow<List<Message>>

    @Query("SELECT * FROM messages WHERE id = :messageId")
    fun getMessageById(messageId: Int): Flow<Message>

    @Upsert
    suspend fun updateMessages(chat: List<Message>)

    @Insert
    suspend fun insertAllMessages(chats: List<Message>)

    @Query("DELETE FROM messages WHERE chatId = :chatId")
    suspend fun deleteMessagesByChatId(chatId: Int)

    @Delete
    suspend fun deleteMessage(message: Message)
}