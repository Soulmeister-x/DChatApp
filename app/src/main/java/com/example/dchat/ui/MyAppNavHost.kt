package com.example.dchat.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "home",
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }
        composable("chats") {
            ChatsScreen(
                navController = navController,
                onNavigateToChat = { chatId -> navController.navigate("chats/$chatId") },
            )
        }
        composable(
            route = "chats/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.IntType })
        ) { backStackEntry ->
            MessagesScreen(
                navController = navController,
                chatId = backStackEntry.arguments?.getInt("chatId"),
            )
        }
        composable(
            route = "contacts"
        ) {
            ContactsScreen(
                navController = navController,
                onContactSelected = { chatId -> navController.navigate("chats/$chatId") }
            )
        }
    }
}
