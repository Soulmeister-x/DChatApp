package com.example.dchat.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dchat.ChatViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/*
class ChatsListFragment : Fragment() {
//    private val vm: ChatViewModel = getViewModel()
    private val vm: ChatViewModel by inject<ChatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            vm.uiState.chatDetailsList.collect { state ->
                // Update UI elements based on the received chats
            }
        }
    }
}

 */