package com.example.dchat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockMessages
import com.example.dchat.ui.theme.DChatTheme

@Composable
fun MessageList(messages: List<Message>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        messages.forEach { msg ->
            MessageItem(msg)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSingleChat() {
    DChatTheme {
        MessageList(mockMessages)
    }
}