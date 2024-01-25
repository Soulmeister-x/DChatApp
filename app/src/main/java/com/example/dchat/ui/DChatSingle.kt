package com.example.dchat.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dchat.data.mockMessages

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DChatSingle(navController: NavHostController, id: Int?) {
    var inputText by remember { mutableStateOf("") }
    Column {
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Zurück zu Chats")
        }
        if (id != null) {
            MessageList(mockMessages)
        }
        Row {
            TextField(value = inputText, onValueChange = { inputText = it })
            Button(onClick = { }) {
                "SEND"
            }
        }
    }
}