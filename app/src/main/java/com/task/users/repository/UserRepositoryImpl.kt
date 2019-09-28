package com.task.users.repository

import com.task.users.api.UsersApiService
import com.task.users.api.convertToUser
import com.task.users.entities.User
import com.task.users.repository.converters.convertToDbUser
import com.task.users.repository.converters.convertToUser
import com.task.users.repository.database.UserDao
import com.task.users.repository.dbEntities.DbUser
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(
    private val usersApiService: UsersApiService,
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Single<List<User>> =
        userDao.getUsers()
            .subscribeOn(Schedulers.io())
            .map { it.map { dbUser -> dbUser.convertToUser() } }
            .filter { it.isNotEmpty() }
            .switchIfEmpty(remoteLoadUsers())

    override fun getUser(id: Int): Single<User> =
        userDao.getUser(id)
            .subscribeOn(Schedulers.io())
            .map { it.convertToUser() }

    override fun getUserFriends(friendsIds: List<Int>): Single<List<User>> =
        userDao.getUserFriends(friendsIds)
            .map { it.map { dbUser -> dbUser.convertToUser() } }
            .subscribeOn(Schedulers.io())

    override fun remoteLoadUsers(): Single<List<User>> =
        usersApiService.getUsers()
            .subscribeOn(Schedulers.io())
            .map { it.map { apiUser -> apiUser.convertToUser() } }
            .doOnSuccess { updateCachedData(it.map { user -> user.convertToDbUser() }) }

    private fun updateCachedData(users: List<DbUser>) {
        userDao.clearTable()
        userDao.insertUsers(users)
    }
}
