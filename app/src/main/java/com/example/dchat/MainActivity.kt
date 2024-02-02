package com.example.dchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dchat.data.mockContacts
import com.example.dchat.data.mockMessages
import com.example.dchat.ui.ChatsViewModel
import com.example.dchat.ui.ContactsViewModel
import com.example.dchat.ui.MyAppNavHost
import com.example.dchat.ui.theme.DChatTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add mock data
        val chatsViewModel: ChatsViewModel = getViewModel()
        chatsViewModel.upsertMessages(mockMessages)
        val contactsViewModel: ContactsViewModel = getViewModel()
        contactsViewModel.upsertContacts(mockContacts)

        setContent {
            navController = rememberNavController()
            DChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost(navController = navController)
                }
            }
        }
    }
}
