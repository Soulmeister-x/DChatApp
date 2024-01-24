package com.example.dchat.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dchat.ChatViewModel
import com.example.dchat.ChatsUiState

// TODO: move AppNavHost to separate file
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "chats",
    viewModel: ChatViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("chats") {
            DChatList(
                modifier = modifier,
                onNavigateToChat = { navController.navigate("chats/{chatId}") },
                uiState.value
                //uiState.getPreviewList()
            )
        }
        composable("chats/{chatId}") { backStackEntry ->
            DChatSingle(
                navController,
                backStackEntry.arguments?.getInt("chatId"),
                //{ vm::updateChat }
            )
        }
        Log.i("log", "NavHostController created")
    }
}