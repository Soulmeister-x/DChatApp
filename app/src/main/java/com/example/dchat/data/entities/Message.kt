package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.Calendar

/**
 * The [Message] data class represents a message.
 * @property id the internal id of this message
 * @property chatId the id of the chat, that this message belongs to
 * @property content a string representation of the content
 * @property senderId the id of the sender
 * @property timestamp a string representation of the time and date, when this message was sent
 */
@Entity(tableName = "messages")
// TODO: rename to DbMessage and make Message domain object
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val chatId: Int,
    val content: String,
    val senderId: Int,
    val timestamp: String,
) {
    fun isSender(): Boolean = this.senderId == 0

    constructor() : this(0,0,"",0, getCurrentTimeStamp())
    constructor(chatId: Int,content: String,senderId: Int):
            this(0,chatId,content,senderId, getCurrentTimeStamp())
    constructor(chatId: Int, content: String): this(0,chatId, content, 0, getCurrentTimeStamp())
}


private fun getCurrentTimeStamp(): String =
    Timestamp(Calendar.getInstance().timeInMillis).toString()
