package com.example.dchat.db

import com.example.dchat.db.entities.Chat
import com.example.dchat.db.entities.Contact
import com.example.dchat.db.entities.Message
import java.sql.Timestamp

val c0 = Contact(0, "X000", "Peter Müller")
val c1 = Contact(1, "X001", "Simone Malzahn")
val c2 = Contact(2, "X002", "Joschka Kakadu")

val m1 = Message(
    id = 1,
    chatId = 0,
    content = "Hello, this is my first message!",
    senderId = 0,
    recipientId = 1,
    timestamp = Timestamp(2024, 10, 4, 12, 30, 0, 0)
)

val m2 = Message(
    id = 2,
    chatId = 0,
    content = "Nice to meet you too!",
    senderId = 1,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 4, 12, 30, 30, 0)
)

val m3 = Message(
    id = 3,
    chatId = 0,
    content = "What's up?",
    senderId = 0,
    recipientId = 1,
    timestamp = Timestamp(2024, 10, 4, 12, 30, 50, 0)
)

val m4 = Message(
    id = 4,
    chatId = 1,
    content = "Hey there, how are you?",
    senderId = 1,
    recipientId = 2,
    timestamp = Timestamp(2024, 10, 5, 10, 0, 0, 0)
)

val m5 = Message(
    id = 5,
    chatId = 1,
    content = "I'm doing great, thanks for asking!",
    senderId = 2,
    recipientId = 1,
    timestamp = Timestamp(2024, 10, 5, 10, 5, 0, 0)
)

val m6 = Message(
    id = 6,
    chatId = 1,
    content = "Nice to hear that! What have you been up to?",
    senderId = 1,
    recipientId = 2,
    timestamp = Timestamp(2024, 10, 5, 10, 10, 0, 0)
)

val m7 = Message(
    id = 7,
    chatId = 2,
    content = "Hi everyone, I'm joining the chat!",
    senderId = 2,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 6, 10, 0, 0, 0)
)

val m8 = Message(
    id = 8,
    chatId = 2,
    content = "Welcome to the chat!",
    senderId = 0,
    recipientId = 2,
    timestamp = Timestamp(2024, 10, 6, 10, 10, 0, 0)
)

val m9 = Message(
    id = 9,
    chatId = 2,
    content = "Thank you! I'm excited to connect with everyone.",
    senderId = 2,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 6, 10, 20, 0, 0)
)


val mockContacts = listOf<Contact>(c0,c1,c2)

val mockChats = listOf<Chat>(
    Chat(
        0,
        listOf(c0, c1),
        listOf(m1, m2, m3)
    ),
    Chat(
        1,
        listOf(c1, c2),
        listOf(m4, m5, m6)
    ),
    Chat(
        2,
        listOf(c0, c2),
        listOf(m7, m8, m9)
    ),
)