package com.example.dchat.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "chats",
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("chats") {
            ChatsScreen(
                modifier = modifier,
                onNavigateToChat = { navController.navigate("chats/{chatId}") },
            )
        }
        composable("chats/{chatId}") { backStackEntry ->
            MessagesScreen(
                navController = navController,
                chatId = backStackEntry.arguments?.getInt("chatId"),
            )
        }
    }
}