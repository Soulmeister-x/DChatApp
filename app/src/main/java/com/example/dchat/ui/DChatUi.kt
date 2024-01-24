package com.example.dchat.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dchat.AppBarDefaults
import com.example.dchat.ChatPreview
import com.example.dchat.ChatsUiState
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockChats
import com.example.dchat.data.testChat
import com.example.dchat.ui.theme.DChatTheme

/**
 * @Composable for displaying the main menu
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DChatAppBar() {
    TopAppBar(
        title = {
            // Title in the middle
            Text(
                text = "DChat",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            // Menu on the right
            Row {
                Switch(
                    checked = false,
                    onCheckedChange = {}
                )
                IconButton(
                    onClick = {
                        // Handle menu click
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        },
        modifier = Modifier.shadow(elevation = AppBarDefaults.TopAppBarElevation),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    )
}

@Composable
fun DChatList(
    modifier: Modifier,
    onNavigateToChat: () -> Unit,
    uiState: ChatsUiState
    //chatPreviewList: List<ChatPreview>
) {
    val chats = uiState.getPreviewList()

    Column {
        DChatAppBar()

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = AppBarDefaults.TopAppBarHeight),
        ) {
            items(chats.size) { id ->
                DChatItem(
                    id = chats[id].id,
                    chatPreview = chats[id].previewMessage,
                    onNavigateToChat = onNavigateToChat
                )
            }
        }
    }
}

@Composable
fun DChatItem(
    chatPreview: String,
    id: Int,
    onNavigateToChat: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp)
            .clickable {
                // Handle click on chat item
                onNavigateToChat.invoke()
            }
    ) {
        // Content of each chat item
        Text(
            text = chatPreview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DChatSingle(navController: NavHostController, id: Int?) {
    val inputText = remember { "" }
    Column {
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Zurück zu Chats")
        }
        if (id != null) {
            MessageList(mockChats[id].messages)
        }
        Row {
            TextField(value = inputText, onValueChange = {})
            Button(onClick = { }) {
                "SEND"
            }
        }
    }
}

@Composable
fun MessageList(messages: List<Message>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        messages.forEach { msg ->
            MessageItem(msg)
        }
    }
}

@Composable
fun MessageItem(msg: Message) {
    val c = if (msg.isSender()) Color.Cyan else Color.Gray
    val arrangement = if (msg.isSender()) Arrangement.End else Arrangement.Start
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(6.dp)
                .shadow(elevation = 4.dp)
                .background(color = c)
        ) {
            Text(
                text = msg.content,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSingleChat() {
    DChatTheme {
        MessageList(testChat.messages)
    }
}