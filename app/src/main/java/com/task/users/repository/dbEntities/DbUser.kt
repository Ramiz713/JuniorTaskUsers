package com.task.users.repository.dbEntities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.task.users.repository.converters.FriendsIdsConverter
import com.task.users.repository.converters.TagsConverter

@Entity(tableName = "user_data")
data class DbUser(
    @PrimaryKey
    val id: Int,
    val guid: String,
    val isActive: Boolean,
    val balance: String,
    val age: Int,
    val name: String,
    val phone: String,
    val eyeColor: String,
    val registered: String,
    val about: String,
    val address: String,
    val company: String,
    val email: String,
    val favoriteFruit: String,
    @TypeConverters(FriendsIdsConverter::class)
    val friends: List<Int>,
    val gender: String,
    val latitude: Double,
    val longitude: Double,
    @TypeConverters(TagsConverter::class)
    val tags: List<String>
)
