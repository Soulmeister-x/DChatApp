package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dchat.util.Converters

/**
 * The [Chat] data class represents a connection of participants.
 * @property id the internal id of this chat
 * @property participants list of participants
 * @property lastMessage copy of last message in this chat for preview purposes
 */
@Entity(tableName = "chats")
@TypeConverters(Converters::class)
data class Chat(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val participants: List<Contact>,
) {
    var lastMessage: Message = Message()
}