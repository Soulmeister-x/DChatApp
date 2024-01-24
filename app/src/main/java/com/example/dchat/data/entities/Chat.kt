package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.dchat.util.Converters

@Entity(tableName = "chats")
@TypeConverters(Converters::class)
data class Chat(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val participants: List<Contact>,
    val messages: List<Message>,
)

