package com.example.dchat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Message
import com.example.dchat.network.ChatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Viewmodel for [ChatsScreen]
 * @param chatsRepository repo that has access to DAOs of database
 */
class MessagesViewModel(
    private val chatsRepository: ChatsRepository,
): ViewModel() {
    private val _chats: MutableStateFlow<MessagesUiState> = MutableStateFlow(MessagesUiState(emptyList()))
    val chats: StateFlow<MessagesUiState> = _chats.asStateFlow()

    init {
        loadData()
    }

    fun upsertMessages(messages: List<Message>) {
        viewModelScope.launch {
            chatsRepository.upsertMessages(messages)
        }
    }

    fun insertMessage(message: Message) {
        viewModelScope.launch {
            chatsRepository.insertMessage(message)
        }
    }

    fun deleteMessage(message: Message) {
        viewModelScope.launch {
            chatsRepository.deleteMessage(message)
        }
    }

    /**
     * This function loads the chat data from the database to [MessagesUiState].
     */
    private fun loadData() {
        viewModelScope.launch {
            chatsRepository.getAllMessages()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect { chatList ->
                    _chats.update {
                        it.copy(messages = chatList)
                    }
                }
        }
    }
}

data class MessagesUiState(val messages: List<Message>) {
    fun getAllChatIds(): Set<Int> = messages.map { it.chatId }.toSet()

    fun getAllChatPreviews(chats: List<Chat>): List<Message> {
        val chatIds = chats.map { it.chatId }
        val ret = mutableListOf<Message>()
        chatIds.forEach { chatId ->
            ret.add(messages.last { chatId == it.chatId })
        }
        return ret
    }

    fun getMessagesByChatId(chatId: Int): List<Message> =
        messages.filter { it.chatId == chatId }

    fun getPreviewMessageByChatId(chatId: Int): Message? =
        messages.lastOrNull { it.chatId == chatId }
}
