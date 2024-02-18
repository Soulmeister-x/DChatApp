package com.example.dchat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import androidx.room.Upsert
import com.example.dchat.data.entities.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query("SELECT * FROM chats")
    fun getAllChats(): Flow<List<Chat>>

    @Upsert
    fun upsertChat(chat: Chat)

    @Insert(onConflict = IGNORE)
    fun upsertChats(chats: List<Chat>)

    @Delete
    fun deleteChat(chat: Chat)

    @Query("DELETE FROM chats")
    fun deleteAllChats()
}
