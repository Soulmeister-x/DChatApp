package com.example.dchat.data.repositories.impl

import com.example.dchat.data.dao.ChatDao
import com.example.dchat.data.dao.MessageDao
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Message
import com.example.dchat.data.repositories.ChatsRepository
import kotlinx.coroutines.flow.Flow

/**
 * The repository acts as an intermediary between the database and the viewmodel.
 * It handles data retrieval and manipulation, shielding the viewmodel from the complexities of
 * database interactions. The repository provides a clean interface for accessing chat data,
 * allowing the viewmodel to focus on UI updates.
 */
class ChatsRepositoryImpl(
    private val messageDao: MessageDao,
    private val chatDao: ChatDao
): ChatsRepository {

    override fun getAllMessagesSortedByChatId(): Flow<List<Message>> =
        messageDao.getAllMessagesSortedByChatId()

    override fun getMessagesByChatId(chatId: Int): Flow<List<Message>> =
        messageDao.getMessagesByChatId(chatId)

    override fun getMessageById(messageId: Int): Flow<Message> =
        messageDao.getMessageById(messageId)

    override fun getChatById(chatId: Int): Flow<Chat> =
        chatDao.getChatById(chatId)

    override suspend fun updateMessages(messages: List<Message>) =
        messageDao.updateMessages(messages)

    override suspend fun insertAllMessages(messages: List<Message>) =
        messageDao.insertAllMessages(messages)

    override suspend fun deleteChatById(chatId: Int) {
        messageDao.deleteMessagesByChatId(chatId)
        chatDao.deleteChatById(chatId)
    }

    override suspend fun deleteMessage(message: Message) {
        messageDao.deleteMessage(message)
    }


}