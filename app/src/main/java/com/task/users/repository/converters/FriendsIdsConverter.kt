package com.task.users.repository.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FriendsIdsConverter {

    @TypeConverter
    fun serialize(friendsIds: List<Int>): String =
        Gson().toJson(friendsIds, object : TypeToken<List<Int>>() {}.type)

    @TypeConverter
    fun deserialize(jsonFriendsIds: String): List<Int> =
        Gson().fromJson(jsonFriendsIds, object : TypeToken<List<Int>>() {}.type)
}
