package com.example.dchat.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey val mid: Int,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "senderId") val senderId: Int
)
