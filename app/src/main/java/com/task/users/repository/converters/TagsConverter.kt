package com.task.users.repository.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TagsConverter {

    @TypeConverter
    fun serialize(tags: List<String>): String =
        Gson().toJson(tags, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun deserialize(jsonTags: String): List<String> =
        Gson().fromJson(jsonTags, object : TypeToken<List<String>>() {}.type)
}
