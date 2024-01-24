package com.example.dchat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dchat.data.AppDataContainer
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockMessages
import com.example.dchat.data.repositories.ChatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

private const val TAG = "ChatViewModel"


/**
 * The viewmodel should manage and present chat data for the UI.
 * It observes the repository's data streams to maintain a consistent view of the chat list.
 * When the chat list changes, the viewmodel updates the UI accordingly.
 * The viewmodel also handles user interactions, such as navigating to chat details or creating new chats.
 */
class ChatViewModel(
    // TODO: Inject repository dependency using Koin
    dataContainer: AppDataContainer
) : ViewModel() {

    private val repository: ChatsRepository

    private var _uiState: MutableStateFlow<UiState>
    var uiState: StateFlow<UiState>

    init {
        repository = dataContainer.chatsRepository
        _uiState = MutableStateFlow(UiState())
        uiState = _uiState.asStateFlow()

        // todo: remove MockData after adding functionalities for adding messages
        runBlocking {
            deleteAllChats()
            insertAllChats(mockMessages)
        }
    }

    /**
     * This function is used to get all the chats from the database.
     * 1. runs in [viewModelScope]
     * 2. calls [Flow] from [ChatsRepository]
     * 3. updates [uiState] / [currentChatList]
     */
    fun fetchAllChats() {
        viewModelScope.launch {
            try {
                repository.getAllMessagesSortedByChatId()
                    .flowOn(Dispatchers.IO)
                    .distinctUntilChanged()
                    .collect { messages: List<Message> ->
                    _uiState.update {
                        //ChatsUiState(chats)
                        it.copy(chatDetailsList = messages)
                    }
                }
            } catch (ioe: IOException) {
                Log.e(TAG, "IOException in fetchAllChats in viewModel:\n${ioe.cause}")
            }
        }
    }

    /**
     * This function is used to add a list of [Chat] to the database.
     * 1. [viewModelScope.launch] is used to launch a coroutine within the viewModel lifecycle.
     * 2. [Dispatchers.IO] is used to change the dispatcher of the coroutine to IO, which is optimal for IO operations, and does not block the main thread.
     * 3. [ChatsRepository.insertAllChats(chatList)] is used to add the chat to the database.
     */
    fun insertAllChats(chatList: List<Message>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllMessages(chatList)
        }
    }

    /**
     * This function is used to remove all chats from the database.
     */
    fun deleteAllChats() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllChats()
        }
    }

    /**
     * This function is used to add a [Chat] to the database.
     * 1. [viewModelScope.launch] is used to launch a coroutine within the viewModel lifecycle.
     * 2. [Dispatchers.IO] is used to change the dispatcher of the coroutine to IO, which is optimal for IO operations, and does not block the main thread.
     * 3. [ChatsRepository.insertChat(chat)] is used to add the chat to the database.
     */
    fun insertChat(chat: List<Message>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertAllMessages(chat)
        }
    }

    /**
     * This function is used to update a [Chat] or add it to the database, if it doesn't exist.
     * 1. [viewModelScope.launch] is used to launch a coroutine within the viewModel lifecycle.
     * 2. [Dispatchers.IO] is used to change the dispatcher of the coroutine to IO, which is optimal for IO operations, and does not block the main thread.
     * 3. [ChatsRepository.updateChat(chat)] is used to update the chat in the database.
     */
    fun updateChat(chat: List<Message>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMessages(chat)
        }
    }
}

data class UiState(
    val chatDetailsList: List<Message> = listOf(),
) {
    fun getPreviewList(): List<ChatPreview> =
        this.chatDetailsList.map { chat ->
            ChatPreview(chat.id, chat.content)
        }
}

//region Details data classes
data class ChatDetails(
    val id: Int = 0,
    val participants: List<ContactDetails> = listOf(),
    val messages: List<MessageDetails> = listOf(),
)

data class ContactDetails(
    val id: Int = 0,
    val code: String,
    val name: String
)

data class MessageDetails(
    val id: Int,
    val chatId: Int,
    val content: String,
    val senderId: Int,
    val recipientId: Int,
    val timestamp: String
)


data class ChatPreview(
    val id: Int = 0,
    val previewMessage: String = ""
)
//endregion

