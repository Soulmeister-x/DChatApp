package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dchat.data.cTest
import com.example.dchat.data.user

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey val id: Int,
    val code: String,
    val name: String,
) {
    companion object {
        fun getMainUser(): Contact = user
        fun getDebugUser(): Contact = cTest
    }
}