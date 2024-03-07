package com.example.dchat.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dchat.data.cTest
import com.example.dchat.data.user

/**
 * The [Contact] data class describes the structure of a contact.
 * @property contactId the internal id of this contact
 * @property code a code that identifies the user to other users
 * @property name the name to be displayed
 */
@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey val contactId: Int,
    val code: String,
    val name: String,
) {
    companion object {
        fun getMainUser(): Contact = user
        fun getDebugUser(): Contact = cTest
    }
}
