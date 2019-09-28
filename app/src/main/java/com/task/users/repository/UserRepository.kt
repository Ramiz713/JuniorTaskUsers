package com.task.users.repository

import com.task.users.entities.User
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository {

    fun getUsers(): Single<List<User>>

    fun getUser(id: Int): Single<User>

    fun getUserFriends(friendsIds: List<Int>): Single<List<User>>

    fun remoteLoadUsers(): Single<List<User>>
}
