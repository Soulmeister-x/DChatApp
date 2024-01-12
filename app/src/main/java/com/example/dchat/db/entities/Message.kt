package com.example.dchat.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val chatId: Int,
    val content: String,
    val senderId: Int,
    val recipientId: Int,
    val timestamp: Timestamp,
)
