package com.example.dchat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dchat.AppBarDefaults
import com.example.dchat.UiState

@Composable
fun DChatList(
    modifier: Modifier,
    onNavigateToChat: () -> Unit,
    uiState: UiState
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