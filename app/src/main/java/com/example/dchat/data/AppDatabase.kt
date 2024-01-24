package com.example.dchat.data

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dchat.DChatApplication
import com.example.dchat.data.dao.ChatDao
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Message
import com.example.dchat.util.Converters
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.coroutineContext

/**
 *  The database serves as the central storage for chat data, persisting and retrieving
 *  chat information. It provides a persistent data layer that ensures data integrity and
 *  availability even when the app is not running.
 */
@Database(entities = [Chat::class, Contact::class, Message::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "chats"
                )
                    .build()
                    .also { INSTANCE = it }

            }
        }
    }
}