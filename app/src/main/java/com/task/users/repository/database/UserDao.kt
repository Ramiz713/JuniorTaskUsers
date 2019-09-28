package com.task.users.repository.database

import androidx.room.*
import com.task.users.repository.dbEntities.DbUser
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user_data")
    fun getUsers(): Maybe<List<DbUser>>

    @Query("SELECT * FROM user_data WHERE id = :id")
    fun getUser(id: Int): Single<DbUser>

    @Query("SELECT * FROM user_data WHERE id IN  (:friendsIds)")
    fun getUserFriends(friendsIds: List<Int>): Single<List<DbUser>>

    @Insert
    fun insertUsers(users: List<DbUser>)

    @Query("DELETE FROM user_data")
    fun clearTable()
}
