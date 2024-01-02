package com.example.dchat.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dchat.db.entities.Chat

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): List<Chat>

    @Query("SELECT * FROM chats WHERE chatId = :chatId")
    fun findChatById(chatId: Int): Chat

    @Insert
    fun insertChat(chat: Chat)

    @Insert
    fun insertAllChats(chats: List<Chat>)

    @Delete
    fun delete(chatId: Int)

}