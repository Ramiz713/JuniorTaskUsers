package com.task.users.entities

data class User(
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
    val friends: List<Int>,
    val gender: String,
    val latitude: Double,
    val longitude: Double,
    val tags: List<String>
)
