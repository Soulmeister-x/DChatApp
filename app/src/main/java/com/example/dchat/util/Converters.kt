package com.example.dchat.util

import androidx.room.TypeConverter
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    //region POJO to JSON
    @TypeConverter
    fun messageToString(msg: Message): String =
        Gson().toJson(msg)

    @TypeConverter
    fun messageListToString(msgs: List<Message>): String =
        Gson().toJson(msgs)

    @TypeConverter
    fun contactsListToString(contacts: List<Contact>): String =
        Gson().toJson(contacts)
    //endregion

    //region JSON to POJO
    @TypeConverter
    fun messageStringToMessage(msgString: String): Message {
        return try {
            Gson().fromJson<Message>(msgString)
        } catch (e: Exception) {
            Message()
        }
    }

    @TypeConverter
    fun messageListStringToMessageList(messagesString: String): List<Message> {
        return try {
            Gson().fromJson<List<Message>>(messagesString)
        } catch (e: Exception) {
            listOf()
        }
    }

    @TypeConverter
    fun contactsStringToContactsList(contactsString: String): List<Contact> {
        return try {
            Gson().fromJson<List<Contact>>(contactsString)
        } catch (e: Exception) {
            listOf()
        }
    }
    //endregion
}