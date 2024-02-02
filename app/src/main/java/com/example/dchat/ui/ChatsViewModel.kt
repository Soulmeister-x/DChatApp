package com.example.dchat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ChatsViewModel(
    private val chatsRepository: ChatsRepository,
): ViewModel() {
    private val _chats: MutableStateFlow<ChatsUiState> = MutableStateFlow(ChatsUiState(emptyList()))
    val chats: StateFlow<ChatsUiState> = _chats.asStateFlow()

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

    fun getAllChatsPreview(): List<Message> {
        val chatIds = chatsRepository.getAllChatIdsINSTANT()
        val chatList: MutableList<Message> = mutableListOf()
        chatIds.forEach { chatId ->
            viewModelScope.launch {
                chatsRepository.getLastMessageByChatId(chatId)
                    .flowOn(Dispatchers.IO)
                    .collect {
                        chatList.add(it)
                    }
            }
        }
        return chatList
    }

    /**
     * This function loads the chat data from the database to [ChatsUiState].
     */
    private fun loadData() {
        viewModelScope.launch {
            chatsRepository.getAllMessages()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect { chatList ->
                    _chats.update {
                        it.copy(chats = chatList)
                    }
                }
        }
    }
}

data class ChatsUiState(val chats: List<Message>) {
    fun getAllChatIds(): Set<Int> = chats.map { it.chatId }.toSet()

    fun getAllChatPreviews(): List<Message> {
        val chatIds = getAllChatIds()
        val ret = mutableListOf<Message>()
        chatIds.forEach { chatId ->
            ret.add(chats.last { chatId == it.chatId })
        }
        return ret
    }

    fun getMessagesByChatId(chatId: Int): List<Message> =
        chats.filter { it.chatId == chatId }
}
