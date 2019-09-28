package com.task.users.repository.converters

import com.task.users.api.apiEntities.ApiFriend
import com.task.users.entities.EyeColor
import com.task.users.entities.FavoriteFruit
import com.task.users.entities.User
import com.task.users.repository.dbEntities.DbUser

fun User.convertToDbUser(): DbUser =
    with(this) {
        DbUser(
            id = id,
            guid = guid,
            isActive = isActive,
            balance = balance,
            age = age,
            name = name,
            phone = phone,
            eyeColor = eyeColor.name,
            registered = registered,
            about = about,
            address = address,
            company = company,
            email = email,
            favoriteFruit = favoriteFruit.name,
            friends = friends,
            gender = gender,
            latitude = latitude,
            longitude = longitude,
            tags = tags
        )
    }


fun DbUser.convertToUser(): User =
    with(this) {
        User(
            id = id,
            guid = guid,
            isActive = isActive,
            balance = balance,
            age = age,
            name = name,
            phone = phone,
            eyeColor = EyeColor.valueOf(eyeColor),
            registered = registered,
            about = about,
            address = address,
            company = company,
            email = email,
            favoriteFruit = FavoriteFruit.valueOf(favoriteFruit),
            friends = friends,
            gender = gender,
            latitude = latitude,
            longitude = longitude,
            tags = tags
        )
    }
