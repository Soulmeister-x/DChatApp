package com.example.dchat.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.mockChats
import com.example.dchat.data.mockContacts
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactsScreen(
    navController: NavHostController,
    onContactSelected: ((Int) -> Unit)?,
    contactsViewModel: ContactsViewModel = koinViewModel(),
    chatsViewModel: ChatsViewModel = koinViewModel()
) {
    val contactsUiState = contactsViewModel.contacts.collectAsStateWithLifecycle()
    val chatsUiState = chatsViewModel.chats.collectAsStateWithLifecycle()
    ContactsScreen(
        navigateBack = { navController.popBackStack() },
        contactsUiState = contactsUiState.value,
        chatsUiState = chatsUiState.value,
        onContactSelected = onContactSelected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    navigateBack: (() -> Unit)?,
    contactsUiState: ContactsUiState,
    chatsUiState: ChatsUiState,
    onContactSelected: ((Int) -> Unit)?,
) {
    val contacts = contactsUiState.contacts

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Button(onClick = { navigateBack?.invoke() }) {
                Text("Back")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues)
                .verticalScroll(rememberScrollState()),
        ) {
            ContactsList(
                contacts = contacts,
                chatsUiState = chatsUiState,
                navigateToChatWithContact = onContactSelected
            )
        }
    }
}

@Composable
fun ContactsList(
    contacts: List<Contact>,
    chatsUiState: ChatsUiState,
    navigateToChatWithContact: ((Int) -> Unit)?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // for each contact show contacts list item
        contacts.forEach {
            ContactsListItem(
                contact = it,
                onContactClick = {
                    val chatId = chatsUiState.findChatIdForContact(it)
                    navigateToChatWithContact?.invoke(chatId)
                }
            )
        }
    }
}

@Composable
fun ContactsListItem(
    contact: Contact,
    onContactClick: ((Contact) -> Unit)?
) {
    val cornerShape = RoundedCornerShape(16.dp)
    Card(
        shape = cornerShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(12.dp, cornerShape)
            .clickable {
                // Handle click on chat item
                onContactClick?.invoke(contact)
            },
        border = BorderStroke(1.dp, Color.Black)
    ) {
        // Content of each chat item
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(60.dp)
                .padding(
                    vertical = 10.dp,
                    horizontal = 8.dp
                )
        ) {
            Text(
                text = "id: ${contact.contactId}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewContactsList() {
    ContactsScreen(
        navigateBack = {},
        contactsUiState = ContactsUiState(mockContacts),
        chatsUiState = ChatsUiState(mockChats),
        onContactSelected = {}
    )
}
