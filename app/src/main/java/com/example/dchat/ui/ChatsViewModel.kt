package com.example.dchat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Contact
import com.example.dchat.network.ChatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatsViewModel(
    private val chatsRepository: ChatsRepository
): ViewModel() {
    private val _chats: MutableStateFlow<ChatsUiState> =
        MutableStateFlow(ChatsUiState(emptyList()))
    val chats = _chats.asStateFlow()

    init {
        loadData()
    }

    /**
     * This function loads the chat data from the database to [MessagesUiState].
     */
    private fun loadData() {
        viewModelScope.launch {
            chatsRepository.getAllChats()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect { memberList ->
                    _chats.update {
                        it.copy(chats = memberList)
                    }
                }
        }
    }

    fun upsertChat(chat: Chat) {
        viewModelScope.launch{
            chatsRepository.upsertChat(chat)
        }
    }

    fun upsertChats(chats: List<Chat>) {
        viewModelScope.launch {
            chatsRepository.upsertChats(chats)
        }
    }

    fun nukeTable() {
        viewModelScope.launch {
            chatsRepository.deleteAllChats()
        }
    }

    fun deleteChat(chat: Chat) {
        viewModelScope.launch {
            chatsRepository
        }
    }

}

data class ChatsUiState(val chats: List<Chat>) {
    fun findChatIdForContact(contact: Contact): Int {
        return chats.find {
            it.participants.size == 1 && it.participants[0] == contact
        }?.chatId ?: 0
    }

    fun getChatIds(): List<Int> {
        return chats.map { it.chatId }
    }
}
