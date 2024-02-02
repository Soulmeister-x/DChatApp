package com.example.dchat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockMessages
import org.koin.androidx.compose.koinViewModel

@Composable
fun MessagesScreen(
    navController: NavHostController,
    chatId: Int?,
    viewModel: ChatsViewModel = koinViewModel()
) {
    val uiState by viewModel.chats.collectAsStateWithLifecycle()
    val messages = if(chatId != null) uiState.getMessagesByChatId(chatId) else listOf()
    MessagesScreen(
        navController = navController,
        chatId = chatId,
        messages = messages,
        sendMessage = viewModel::insertMessage,
        deleteMessage = viewModel::deleteMessage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    navController: NavHostController,
    chatId: Int?,
    messages: List<Message>,
    sendMessage: (Message) -> Unit,
    deleteMessage: (Message) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(topBar = {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween) {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Zurück zu Chats")
            }
            Text(text = "chatId: $chatId")
        }
    },
        bottomBar = {Row {
            TextField(value = inputText, onValueChange = { inputText = it })
            Button(
                onClick = {
                    if (chatId != null) {
                        sendMessage.invoke(Message(chatId, inputText))
                        inputText = ""
                    }
                }
            ) {
                Text("SEND")
            }
        }}
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Bottom
        ) {
            if (chatId == null) {
                Column {
                    Text(text = "Mocked data, because chatId == null", style = MaterialTheme.typography.headlineMedium)
                    MessageList(mockMessages,{})
                }
            } else {
                MessageList(messages = messages, deleteMessage = deleteMessage)
            }
        }
    }
}