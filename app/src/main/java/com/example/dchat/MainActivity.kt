package com.example.dchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.dchat.data.AppDataContainer
import com.example.dchat.data.AppDatabase
import com.example.dchat.ui.MyAppNavHost
import com.example.dchat.ui.theme.DChatTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {
    private lateinit var appDb: AppDatabase
    private lateinit var appDataContainer: AppDataContainer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appDataContainer = AppDataContainer(applicationContext)
        appDb = AppDatabase.getInstance(applicationContext)
        //val viewModel = ChatViewModel(appDataContainer)
        //val viewModel : ChatViewModel by inject<ChatViewModel>()
        //val viewModel: ChatViewModel = getViewModel<ChatViewModel>()
        //val viewModel: ChatViewModel by viewModels()

        val viewModel: ChatViewModel = getViewModel()

        // update uiState
        viewModel.fetchAllChats()


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
    }
}
