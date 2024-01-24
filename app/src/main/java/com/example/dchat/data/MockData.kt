package com.example.dchat.data

import com.example.dchat.data.entities.Chat
import com.example.dchat.data.entities.Contact
import com.example.dchat.data.entities.Message
import java.sql.Timestamp

val user = Contact(0, "X000", "Main User")

val c1 = Contact(1, "X001", "Simone Malzahn")
val c2 = Contact(2, "X002", "Joschka Kakadu")
val c3 = Contact(3, "X003", "Peter Müller")
val cTest = Contact(99, "X099", "TEST Name")

//region messages
val m00 = Message(
    id = 99,
    chatId = 99,
    content = "TEST TEST TEST",
    senderId = cTest.id,
    recipientId = user.id,
    timestamp = Timestamp(2099, 1,1,23,59,59,0).toString()
)

val m01 = Message(
    id = 99,
    chatId = 99,
    content = "user TEST TEST TEST",
    senderId = user.id,
    recipientId = cTest.id,
    timestamp = Timestamp(2099, 1,1,23,59,59,0).toString()
)

val m1 = Message(
    id = 1,
    chatId = 0,
    content = "Hello, this is my first message!",
    senderId = 0,
    recipientId = 1,
    timestamp = Timestamp(2024, 10, 4, 12, 30, 0, 0).toString()
)

val m2 = Message(
    id = 2,
    chatId = 0,
    content = "Nice to meet you too!",
    senderId = 1,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 4, 12, 30, 30, 0).toString()
)

val m3 = Message(
    id = 3,
    chatId = 0,
    content = "What's up?",
    senderId = 0,
    recipientId = 1,
    timestamp = Timestamp(2024, 10, 4, 12, 30, 50, 0).toString()
)

val m4 = Message(
    id = 4,
    chatId = 1,
    content = "Hey there, how are you?",
    senderId = 0,
    recipientId = 2,
    timestamp = Timestamp(2024, 10, 5, 10, 0, 0, 0).toString()
)

val m5 = Message(
    id = 5,
    chatId = 1,
    content = "I'm doing great, thanks for asking!",
    senderId = 2,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 5, 10, 5, 0, 0).toString()
)

val m6 = Message(
    id = 6,
    chatId = 1,
    content = "Nice to hear that! What have you been up to?",
    senderId = 0,
    recipientId = 2,
    timestamp = Timestamp(2024, 10, 5, 10, 10, 0, 0).toString()
)

val m7 = Message(
    id = 7,
    chatId = 2,
    content = "Hi everyone, I'm joining the chat!",
    senderId = 3,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 6, 10, 0, 0, 0).toString()
)

val m8 = Message(
    id = 8,
    chatId = 2,
    content = "Welcome to the chat!",
    senderId = 0,
    recipientId = 3,
    timestamp = Timestamp(2024, 10, 6, 10, 10, 0, 0).toString()
)

val m9 = Message(
    id = 9,
    chatId = 2,
    content = "Thank you! I'm excited to connect with everyone.",
    senderId = 3,
    recipientId = 0,
    timestamp = Timestamp(2024, 10, 6, 10, 20, 0, 0).toString()
)
//endregion

val mockContacts = listOf<Contact>(c3,c1,c2)

val testChat = Chat(
    id = 99,
    participants = listOf(cTest, user),
)


val mockChats = listOf<Chat>(
    Chat(
        0,
        listOf(user, c1),
    ),
    Chat(
        1,
        listOf(user, c2),
    ),
    Chat(
        2,
        listOf(user, c3),
    ),
)


val mockMessages = listOf<Message>(
    m00,m01,m1,m2,m3,m4,m5,m6,m7,m8,m9
)