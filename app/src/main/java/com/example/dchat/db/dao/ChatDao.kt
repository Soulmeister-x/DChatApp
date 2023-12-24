package com.example.dchat.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dchat.db.Message

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): List<Message>

    @Query("SELECT * FROM chats WHERE chatId = :chatId")
    fun findById(chatId: Int): List<Message>

    @Insert
    fun insertChat(chat: List<Message>)

    @Insert
    fun insertAllChats(vararg chats: List<Message>)

    @Delete
    fun delete(chatId: Int)

}