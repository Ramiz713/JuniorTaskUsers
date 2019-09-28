package com.task.users.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.task.users.repository.converters.FriendsIdsConverter
import com.task.users.repository.converters.TagsConverter
import com.task.users.repository.dbEntities.DbUser

@Database(entities = [DbUser::class], version = 1)
@TypeConverters(FriendsIdsConverter::class, TagsConverter::class)
abstract class AppDatabase:  RoomDatabase() {

    abstract fun userDao(): UserDao
}
