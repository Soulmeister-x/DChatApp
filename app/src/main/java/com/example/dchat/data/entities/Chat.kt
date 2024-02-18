package com.example.dchat.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Defines relation between [Contact] and [Message]-[chatId].
 */

@Entity(tableName = "chats")
data class Chat(
    @PrimaryKey(autoGenerate = true) val chatId: Int,
    val participants: List<Contact>
) {
    constructor(participant: Contact) : this(
        chatId = 0,
        participants = listOf(participant)
    )
    constructor(chatId: Int,participant: Contact) : this(
        chatId = chatId,
        participants = listOf(participant)
    )
}
