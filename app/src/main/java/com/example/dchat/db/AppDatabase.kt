package com.example.dchat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dchat.db.dao.ChatDao
import com.example.dchat.db.entities.Chat

@Database(entities = [Chat::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    // singleton for lower resource consumption
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) return tmpInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "chats"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}