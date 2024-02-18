package com.example.dchat.network

import com.example.dchat.data.AppDataContainer
import com.example.dchat.data.dao.ChatDao
import com.example.dchat.data.dao.ContactDao
import com.example.dchat.data.dao.MessageDao
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Message
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ChatsRepository : KoinComponent {
    suspend fun getAllChatIds(): Flow<List<Int>>
    suspend fun getAllMessages(): Flow<List<Message>>
    suspend fun getLastMessageByChatId(chatId: Int): Flow<Message>
    suspend fun getAllMessagesByChatId(chatId: Int): Flow<List<Message>>
    suspend fun upsertMessages(messages: List<Message>)
    suspend fun insertMessage(message: Message)
    suspend fun deleteMessage(message: Message)
    suspend fun getAllContacts(): Flow<List<Contact>>
    suspend fun upsertContacts(contacts: List<Contact>)
    suspend fun getAllChats(): Flow<List<Chat>>
    suspend fun upsertChat(chat: Chat)
    suspend fun upsertChats(chats: List<Chat>)
    suspend fun deleteChat(chat: Chat)
    suspend fun deleteAllChats()
}

/**
 * The repository acts as an intermediary between the database and the viewmodel.
 * It handles data retrieval and manipulation, shielding the viewmodel from the complexities of
 * database interactions. The repository provides a clean interface for accessing chat data,
 * allowing the viewmodel to focus on UI updates.
 */
class ChatsRepositoryImpl : ChatsRepository {

    private val appDataContainer: AppDataContainer by inject()
    private val messageDao: MessageDao = appDataContainer.appDb.messageDao()
    private val contactDao: ContactDao = appDataContainer.appDb.contactDao()
    private val chatDao: ChatDao = appDataContainer.appDb.chatDao()


    override suspend fun getAllChatIds(): Flow<List<Int>> {
        return messageDao.getAllChatIds()
    }

    override suspend fun getAllMessages(): Flow<List<Message>> {
        return messageDao.getAllMessages()
    }

    override suspend fun getLastMessageByChatId(chatId: Int): Flow<Message> {
        return messageDao.getLastMessageByChatId(chatId)
    }


    override suspend fun getAllMessagesByChatId(chatId: Int): Flow<List<Message>> {
        return messageDao.getMessagesByChatId(chatId)
    }

    override suspend fun upsertMessages(messages: List<Message>) {
        messageDao.upsertMessages(messages)
    }

    override suspend fun insertMessage(message: Message) {
        messageDao.insertMessage(message)
    }

    override suspend fun deleteMessage(message: Message) {
        messageDao.deleteMessage(message)
    }

    override suspend fun getAllContacts(): Flow<List<Contact>> {
        return contactDao.getAllContacts()
    }

    override suspend fun upsertContacts(contacts: List<Contact>) {
        contacts.forEach {
            contactDao.upsertContact(it)
        }
    }

    override suspend fun getAllChats(): Flow<List<Chat>> {
        return chatDao.getAllChats()
    }

    override suspend fun upsertChat(chat: Chat) {
        chatDao.upsertChat(chat)
    }

    override suspend fun upsertChats(chats: List<Chat>) {
        chatDao.upsertChats(chats)
    }

    override suspend fun deleteChat(chat: Chat) {
        messageDao.deleteMessagesByChatId(chat.chatId)
        chatDao.deleteChat(chat)
    }

    override suspend fun deleteAllChats() {
        chatDao.deleteAllChats()
    }

}