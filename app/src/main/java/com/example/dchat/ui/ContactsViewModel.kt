package com.example.dchat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Message
import com.example.dchat.network.ChatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val chatsRepository: ChatsRepository
): ViewModel() {
    private val _contacts: MutableStateFlow<ContactsUiState> =
        MutableStateFlow(ContactsUiState(emptyList()))
    val contacts = _contacts.asStateFlow()

    init {
        loadContacts()
    }

    /**
     * This function loads the chat data from the database to [ChatsUiState].
     */
    private fun loadContacts() {
        viewModelScope.launch {
            chatsRepository.getAllContacts()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect { contactsList ->
                    _contacts.update {
                        it.copy(contacts = contactsList)
                    }
                }
        }
    }

    fun upsertContacts(contacts: List<Contact>) {
        viewModelScope.launch {
            chatsRepository.upsertContacts(contacts)
        }
    }
}

data class ContactsUiState(val contacts: List<Contact>)
