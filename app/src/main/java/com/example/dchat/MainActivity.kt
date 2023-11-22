package com.example.dchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dchat.ui.theme.DChatTheme


// TODO: move AppNavHost to separate file
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "chats"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("chats") {
            DChatList(
                modifier = modifier,
                onNavigateToChat = { navController.navigate("chats/{chatId}")}
            )
        }
        composable("chats/{chatId}") { backStackEntry ->
            DChatSingle(navController, backStackEntry.arguments?.getInt("chatId"))
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DChatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DChatAppBar() {
    TopAppBar(
        title = {
            // Title in the middle
            Text(
                text = "DChat",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            // Menu on the right
            Row {
                Switch(
                    checked = false,
                    onCheckedChange = {}
                )
                IconButton(
                    onClick = {
                        // Handle menu click
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        },
        modifier = Modifier.shadow(elevation = AppBarDefaults.TopAppBarElevation),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    )
}

@Composable
fun DChatList(
    modifier: Modifier,
    onNavigateToChat: () -> Unit
) {
    // Dummy chat data
    val chatList = remember {
        mutableListOf(
            Pair(0,"Chat 1"),
            Pair(1,"Chat 2"),
            Pair(2,"Chat 3"),
            // Add more chats as needed
        )
    }

    Column {
        DChatAppBar()
        //Text(text = "Welcome to DChat", style = MaterialTheme.typography.headlineMedium)

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = AppBarDefaults.TopAppBarHeight),
        ) {
            items(chatList) { chat ->
                DChatItem(id = chat.first, chat = chat.second, onNavigateToChat = onNavigateToChat)
            }
        }
    }
}

@Composable
fun DChatItem(
    chat: String,
    id: Int,
    onNavigateToChat: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp)
            .clickable {
                // Handle click on chat item
                onNavigateToChat.invoke()
            }
    ) {
        // Content of each chat item
        Text(
            text = chat,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

class MessageObject(
    val id: Int = -1,
    val text: String = "",
    val isSender: Boolean = false
)

@Composable
fun DChatSingle(navController: NavHostController, id: Int?) {
    Column {
        Button(onClick = {navController.popBackStack()}) {
            Text(text = "Zurück zu Chats")
        }
        if (id != null) {
            SingleChat(id)
        }
    }
}

@Composable
fun SingleChat(id: Int = -1) {
    // retrieve chat from database by id
    val chatList = remember {
        mutableListOf<MessageObject>(
            MessageObject(0, "Hello X", true),
            MessageObject(1, "Hello Y", false),
            MessageObject(2, "Good Bye", true),
        )
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        chatList.forEach { msg ->
            SingleChatLine(msg)
        }
    }
}

@Composable
fun SingleChatLine(msg: MessageObject) {
    val c = if (msg.isSender) Color.Cyan else Color.Gray
    val offset = if (msg.isSender) 40.dp else 0.dp
    Box(
        modifier = Modifier.offset(x = offset)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8F)
                .padding(12.dp)
                .background(color = c)
                .shadow(elevation = 6.dp)
        ) {
            Text(text = msg.text)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDChatApp() {
    DChatTheme {
        MyAppNavHost()
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSingleChat() {
    DChatTheme {
        SingleChat()
    }
}