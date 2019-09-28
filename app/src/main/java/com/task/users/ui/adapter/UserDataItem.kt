package com.task.users.ui.adapter

import com.task.users.entities.User

sealed class UserDataItem {
    abstract val id: Int

    data class UserItem(val user: User) : UserDataItem() {
        override val id = user.id
    }

    data class UserDetailsItem(val user: User) : UserDataItem() {
        override val id = user.id
    }

    data class Header(val header: String) : UserDataItem() {
        override val id = Int.MIN_VALUE
    }
}
