package com.example.dchat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dchat.data.entities.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): Flow<List<Chat>>

    @Query("SELECT * FROM chats WHERE id = :chatId")
    fun getChatById(chatId: Int): Flow<Chat>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChat(chat: Chat)

    @Update
    suspend fun updateChat(chat: Chat)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllChats(chats: List<Chat>)

    @Delete
    suspend fun deleteChat(chat: Chat)

}