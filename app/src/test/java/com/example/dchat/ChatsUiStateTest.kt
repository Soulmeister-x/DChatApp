package com.example.dchat

import com.example.dchat.data.entities.Message
import com.example.dchat.ui.ChatsUiState
import org.junit.Test

class ChatsUiStateTest {
    private val testState = ChatsUiState(
        listOf(
            Message(1,"msg 1 from chat 1"),
            Message(1,"msg 2 from chat 1"),
            Message(2,"msg 1 from chat 2"),
            Message(2,"msg 2 from chat 2"),
            Message(3,"msg 1 from chat 3"),
        )
    )

    @Test
    fun filter_messages_by_chatId() {
        val testChatId = 2

        // assert that multiple chat Ids are present in test data
        assert(testState.getAllChatIds().size > 1)

        // assert that the result only contains messages with chatId 2
        val filteredMessagesState = ChatsUiState(testState.getMessagesByChatId(testChatId))
        assert(filteredMessagesState.chats.size == 2)

        val filteredChatIds = filteredMessagesState.getAllChatIds()
        assert(filteredChatIds.size == 1)
        assert(filteredChatIds.contains(testChatId))
    }
}