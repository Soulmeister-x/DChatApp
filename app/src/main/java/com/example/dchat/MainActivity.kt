package com.example.dchat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.example.dchat.data.AppDataContainer
import com.example.dchat.data.AppDatabase
import com.example.dchat.data.dao.ChatDao
import com.example.dchat.data.entities.Chat
import com.example.dchat.data.mockChats
import com.example.dchat.data.repositories.ChatsRepository
import com.example.dchat.data.repositories.impl.ChatsRepositoryImpl
import com.example.dchat.ui.MyAppNavHost
import com.example.dchat.ui.theme.DChatTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private lateinit var appDb: AppDatabase
    private lateinit var appDao: ChatDao
    private lateinit var appDataContainer: AppDataContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("log", "MainActivity onCreate STARTED")

        appDataContainer = AppDataContainer(applicationContext)
        appDb = AppDatabase.getInstance(applicationContext)
        appDao = appDb.chatDao()

        val viewModel = ChatViewModel(appDataContainer)

        // update uiState
        viewModel.fetchAllChats()

        //val viewModel : ChatViewModel by inject<ChatViewModel>()
        //val viewModel: ChatViewModel = getViewModel<ChatViewModel>()
        //val viewModel: ChatViewModel by viewModels()

        setContent {
            DChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost(viewModel = viewModel)
                }
            }
        }
        Log.i("log", "MainActivity onCreate FINISH")

    }
}


class MessageObject(
    val id: Int = -1,
    val text: String = "",
    val isSender: Boolean = false
)

