package com.example.dchat.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockMessages
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatsScreen(
    onNavigateToChat: (Int) -> Unit,
    viewModel: ChatsViewModel = koinViewModel()
) {
    val uiState by viewModel.chats.collectAsStateWithLifecycle()
    val chats = uiState.getAllChatPreviews()
    ChatsScreen(
        chatsUiState = ChatsUiState(chats),
        onChatClick = onNavigateToChat
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    chatsUiState: ChatsUiState,
    onChatClick: (Int) -> Unit
) {
    Scaffold(
        topBar = { DChatAppBar() },
        content = {
            ChatsList(
                chatList = chatsUiState.chats,
                onChatClick = onChatClick,
                paddingValues = it
            )
        }
    )
}


@Composable
fun ChatsList(
    chatList: List<Message>,
    onChatClick: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues = paddingValues),
    ) {
        chatList.forEach{ message ->
            ChatsListEntry(message = message, onChatClick = onChatClick)
        }
    }
}

@Composable
fun ChatsListEntry(
    message: Message,
    onChatClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp)
            .clickable {
                // Handle click on chat item
                onChatClick.invoke(message.chatId)
            }
    ) {
        // Content of each chat item
        Row {
            Column(modifier = Modifier.weight(0.8F)) {
                Text(
                    text = "chatId: ${message.chatId}",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            Column(modifier = Modifier.weight(0.2F)) {
                Text(
                    text = message.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .align(alignment = Alignment.End)
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewChatsScreen() {
    ChatsScreen(
        chatsUiState = ChatsUiState(mockMessages),
        onChatClick = {}
    )
}