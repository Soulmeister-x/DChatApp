package com.example.dchat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dchat.data.dao.ContactDao
import com.example.dchat.data.dao.MessageDao
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Message
import com.example.dchat.util.Converters

/**
 *  The database serves as the central storage for chat data, persisting and retrieving
 *  chat information. It provides a persistent data layer that ensures data integrity and
 *  availability even when the app is not running.
 */
@Database(entities = [Contact::class, Message::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun contactDao(): ContactDao
}