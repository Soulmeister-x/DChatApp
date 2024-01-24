package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val chatId: Int,
    val content: String,
    val senderId: Int,
    val recipientId: Int,
    val timestamp: String,
) {
    fun isSender(): Boolean = this.senderId == 0
}
