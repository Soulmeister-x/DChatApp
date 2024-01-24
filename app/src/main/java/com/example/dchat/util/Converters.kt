package com.example.dchat.util

import androidx.room.TypeConverter
import com.example.dchat.ChatDetails
import com.example.dchat.ChatPreview
import com.example.dchat.ContactDetails
import com.example.dchat.MessageDetails
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    @TypeConverter
    fun messageListToString(msgs: List<Message>): String =
        Gson().toJson(msgs)

    @TypeConverter
    fun contactsListToString(contacts: List<Contact>): String =
        Gson().toJson(contacts)


    @TypeConverter
    fun contactsStringToContactsList(contactsString: String): List<Contact> {
        /*
        val tmp = contactsString
            .replace("[", "")
            .replace("]", "")
            .replace(" ", "")

        tmp.split(",").forEach {
        }
         */

        return try {
            Gson().fromJson<List<Contact>>(contactsString)
        } catch (e: Exception) {
            listOf()
        }
    }

    @TypeConverter
    fun messagesStringToMessageList(messagesString: String): List<Message> {
        return try {
            Gson().fromJson<List<Message>>(messagesString)
        } catch (e: Exception) {
            listOf()
        }
    }


    //region Converters: SQL -> Details
    @TypeConverter
    fun toMessageDetails(msg: Message): MessageDetails {
        with(msg) {
            return MessageDetails(id, chatId, content, senderId, recipientId, timestamp)
        }
    }
    @TypeConverter
    fun toContactDetails(c: Contact): ContactDetails {
        with(c) {
            return ContactDetails(id, code, name)
        }
    }
    @TypeConverter
    fun toChatDetails(c: Chat): ChatDetails {
        with(c) {
            return ChatDetails(
                id,
                participants.map { toContactDetails(it) },
                messages.map { toMessageDetails(it) }
            )
        }
    }
    /*
    fun Message.toMessageDetails(): MessageDetails = MessageDetails(
        id, chatId, content, senderId, recipientId, timestamp
    )

    @TypeConverter
    fun Contact.toContactDetails(): ContactDetails = ContactDetails(
        id, code, name
    )
    /*
    fun Chat.toChatDetails(): ChatDetails = ChatDetails(
        id = id,
        participants = participants.map { it.toContactDetails() },
        messages = messages.map { it.toMessageDetails() }
    )
     */
    @TypeConverter
    fun Chat.toChatDetails(): ChatDetails = ChatDetails(
        id = id,
        participants = participants.contacts.map { it.toContactDetails() },
        messages = messages.messages.map { it.toMessageDetails() }
    )
     */
    //endregion

    //region Converters: Details -> SQL
    /*
    @TypeConverter
    fun MessageDetails.toMessage(): Message = Message(
        id, chatId, content, senderId, recipientId, timestamp
    )
    @TypeConverter
    fun ContactDetails.toContact(): Contact = Contact(
        id, code, name
    )
    /*
    fun ChatDetails.toChat(): Chat = Chat(
        id = id,
        participants = participants.map { it.toContact() },
        messages = messages.map { it.toMessage() }
    )
     */
    @TypeConverter
    fun ChatDetails.toChat(): Chat = Chat(
        id = id,
        participants = ContactList(participants.map { it.toContact() }),
        messages = MessageList(messages.map { it.toMessage() })
    )

     */
    @TypeConverter
    fun detailsToMessage(m: MessageDetails): Message {
        with(m) {
            return Message(id, chatId, content, senderId, recipientId, timestamp)
        }
    }

    @TypeConverter
    fun detailsToContact(c: ContactDetails): Contact {
        with(c) {
            return Contact(id, code, name)
        }
    }

    @TypeConverter
    fun detailsToChat(c: ChatDetails): Chat {
        with(c) {
            return Chat(
                id,
                participants.map { detailsToContact(it) },
                messages.map { detailsToMessage(it) }
            )
        }
    }
    //endregion


    /**
     * Function for converting the details class to the minimal preview class.
     */
    fun ChatDetails.toPreview(): ChatPreview = ChatPreview(
        id = id,
        previewMessage = messages.last().content
    )
}