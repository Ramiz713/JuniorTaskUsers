package com.task.users.api.apiEntities

import com.task.users.entities.EyeColor
import com.task.users.entities.FavoriteFruit

data class ApiUser(
    val id: Int,
    val guid: String,
    val isActive: Boolean,
    val balance: String,
    val age: Int,
    val name: String,
    val phone: String,
    val eyeColor: EyeColor,
    val registered: String,
    val about: String,
    val address: String,
    val company: String,
    val email: String,
    val favoriteFruit: FavoriteFruit,
    val friends: List<ApiFriend>,
    val gender: String,
    val latitude: Double,
    val longitude: Double,
    val tags: List<String>
)
