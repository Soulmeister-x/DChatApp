package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The [Message] data class represents a message, that belongs to a [Chat].
 * @property id the internal id of this message
 * @property chatId the id of the chat, that this message belongs to
 * @property content a string representation of the content
 * @property senderId the id of the sender
 * @property recipientId the id of the recipient
 * @property timestamp a string representation of the time and date, when this message was sent
 */
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

    constructor() : this(0,0,"",0,0,"")
}
