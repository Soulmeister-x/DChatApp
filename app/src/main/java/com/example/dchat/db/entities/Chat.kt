package com.example.dchat.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_table")
data class Chat(
    @PrimaryKey(autoGenerate = true) val chatId: Int,
    @ColumnInfo val messages: List<Message>,
)

