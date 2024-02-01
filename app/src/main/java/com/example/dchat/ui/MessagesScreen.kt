package com.example.dchat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
        sendMessage = viewModel::insertMessage
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    navController: NavHostController,
    chatId: Int?,
    messages: List<Message>,
    sendMessage: (Message) -> Unit
) {
    var inputText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Zurück zu Chats")
        }
        if (chatId == null) {
            Column {
                Text(text = "Mocked data, because chatId == null", style = MaterialTheme.typography.headlineMedium)
                MessageList(mockMessages)
            }
        } else {
            MessageList(messages = messages)
            Row {
                TextField(value = inputText, onValueChange = { inputText = it })
                Button(
                    onClick = { sendMessage.invoke(Message(chatId, inputText)) }
                    //{ sendMessage(message) }
                ) {
                    Text("SEND")
                }
            }
        }
    }
}