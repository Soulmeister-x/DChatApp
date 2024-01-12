package com.example.dchat

import androidx.lifecycle.ViewModel
import com.example.dchat.repositories.ChatsRepository
import com.example.dchat.db.entities.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel constructor(
    // Inject repository dependency using Koin
    repository: ChatsRepository
) : ViewModel() {

    private val chats: Flow<List<Chat>>

    init {
        chats = repository.getAllChats()
    }

    private val _uiState = MutableStateFlow(
        ChatsUiState(chats)
    )
    val uiState: StateFlow<ChatsUiState> = _uiState.asStateFlow()

}