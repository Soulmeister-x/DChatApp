package com.example.dchat.network

import com.example.dchat.data.AppDataContainer
import com.example.dchat.data.dao.ContactDao
import com.example.dchat.data.dao.MessageDao
import com.example.dchat.data.entities.Message
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface ChatsRepository : KoinComponent {
    fun getAllChatIdsINSTANT(): List<Int>
    suspend fun getAllChatIds(): Flow<List<Int>>
    suspend fun getAllMessages(): Flow<List<Message>>
    suspend fun getLastMessageByChatId(chatId: Int): Flow<Message>
    suspend fun getAllMessagesByChatId(chatId: Int): Flow<List<Message>>
    suspend fun upsertMessages(messages: List<Message>)
    suspend fun insertMessage(message: Message)
}
/*
    suspend fun deleteAllChats()
    fun getAllMessagesSortedByChatId(): Flow<List<Message>>
    fun getAllChats(): Flow<List<Chat>>
    fun getMessagesByChatId(chatId: Int): Flow<List<Message>>
    fun getMessageById(messageId: Int): Flow<Message>
    fun getChatById(chatId: Int): Flow<Chat>
    suspend fun updateMessages(messages: List<Message>)
    suspend fun insertMessage(message: Message)
    suspend fun insertAllMessages(messages: List<Message>)
    suspend fun deleteAllChats()
    suspend fun deleteChatById(chatId: Int)
    suspend fun deleteMessage(message: Message)
}

 */

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


    override fun getAllChatIdsINSTANT(): List<Int> {
        return messageDao.getChatIds()
    }

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


    /*
    override suspend fun deleteAllChats() {
        messageDao.deleteAllMessages()
        chatDao.deleteAllChats()
    }
        override fun getAllMessagesSortedByChatId(): Flow<List<Message>> =
            messageDao.getAllMessagesSortedByChatId()

        override fun getAllChats(): Flow<List<Chat>> {
            TODO("Not yet implemented")
        }

        override fun getMessagesByChatId(chatId: Int): Flow<List<Message>> =
            messageDao.getMessagesByChatId(chatId)

        override fun getMessageById(messageId: Int): Flow<Message> =
            messageDao.getMessageById(messageId)

        override fun getChatById(chatId: Int): Flow<Chat> =
            chatDao.getChatById(chatId)

        override suspend fun updateMessages(messages: List<Message>) =
            messageDao.updateMessages(messages)

        override suspend fun insertMessage(message: Message) {
            messageDao.insertMessage(message)
        }

        override suspend fun insertAllMessages(messages: List<Message>) =
            messageDao.insertAllMessages(messages)


        override suspend fun deleteChatById(chatId: Int) {
            messageDao.deleteMessagesByChatId(chatId)
            chatDao.deleteChatById(chatId)
        }

        override suspend fun deleteMessage(message: Message) {
            messageDao.deleteMessage(message)
        }
     */
}