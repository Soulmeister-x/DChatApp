package com.example.dchat.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dchat.db.dao.ChatDao

@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}