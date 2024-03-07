package com.example.dchat.ui

import android.util.Log
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
import androidx.compose.material3.FloatingActionButton
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
import androidx.navigation.NavHostController
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockChatsWithPreviewMessage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatsScreen(
    navController: NavHostController,
    onNavigateToChat: (Int) -> Unit,
    messagesViewModel: MessagesViewModel = koinViewModel(),
    contactsViewModel: ContactsViewModel = koinViewModel(),
    chatsViewModel: ChatsViewModel = koinViewModel()
    ) {
    val messagesUiState by messagesViewModel.chats.collectAsStateWithLifecycle()
    //val contactsUiState by contactsViewModel.contacts.collectAsStateWithLifecycle()
    val chatsUiState by chatsViewModel.chats.collectAsStateWithLifecycle()
    val chats = chatsUiState.chats

    val previewUiState = ChatsListUiState(
        chats.associateWith { chat ->
            val msg = messagesUiState.getPreviewMessageByChatId(chat.chatId) ?: Message()
            Log.i("log","mapping $chat to $msg")
            msg
        }
    )

    ChatsScreen(
        chatsListUiState = previewUiState,
        onChatClick = onNavigateToChat,
        startChatFromContacts = { navController.navigate("contacts") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    chatsListUiState: ChatsListUiState,
    onChatClick: (Int) -> Unit,
    startChatFromContacts: () -> Unit,
) {
    Scaffold(
        topBar = { DChatAppBar() },
        content = {
            ChatsList(
                chatListUiState = chatsListUiState,
                onChatClick = onChatClick,
                paddingValues = it
            )
        },
        floatingActionButton = { FloatingActionButton(
            onClick = startChatFromContacts
        ) {
            Text(text = "+")
        } }
    )
}

/**
 * A composable screen for displaying a list of chats
 * for preview-purposes. Every entry should include
 * [chatId],[contact.name],[messages.last],[timestamp]
 */
@Composable
fun ChatsList(
    chatListUiState: ChatsListUiState,
    onChatClick: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(paddingValues = paddingValues),
    ) {
        chatListUiState.chats.forEach { (chat, message) ->
            ChatsListEntry(message = message, chat.participants[0].name,onChatClick = onChatClick)
        }
    }
}

/**
 * Single entry of [ChatsList], displaying:
 * [chatId],[contact.name],[messages.last],[timestamp]
 */
@Composable
fun ChatsListEntry(
    message: Message,
    senderName: String,
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
                    text = "chatId: ${message.chatId} | sender: $senderName",
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
        chatsListUiState = ChatsListUiState(mockChatsWithPreviewMessage),
        onChatClick = {},
        startChatFromContacts = {}
    )
}

/**
 * A data class for associating a [Chat] with it's last [Message].
 */
data class ChatsListUiState(
    val chats: Map<Chat,Message>
)
