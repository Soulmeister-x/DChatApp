package com.example.dchat.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dchat.data.entities.Message
import com.example.dchat.data.mockMessages
import com.example.dchat.ui.theme.DChatTheme

@Composable
fun MessageList(
    messages: List<Message>,
    deleteMessage: ((Message) -> Unit)?
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        messages.forEach { msg ->
            MessageListItem(
                msg = msg,
                onLongClick = deleteMessage,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSingleChat() {
    DChatTheme {
        MessageList(mockMessages,{})
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MessageListItem(
    msg: Message,
    onLongClick: ((Message) -> Unit)?
) {
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
                .combinedClickable(
                    onClick = {},
                    onLongClick = { onLongClick?.invoke(msg) },
                    onDoubleClick = {}
                )
        ) {
            Text(
                text = msg.content,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}