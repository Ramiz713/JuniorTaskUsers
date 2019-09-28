package com.task.users.api

import com.task.users.api.apiEntities.ApiUser
import com.task.users.entities.User

fun ApiUser.convertToUser(): User =
    with(this) {
        User(
            id = id,
            guid = guid,
            isActive = isActive,
            balance = balance,
            age = age,
            name = name,
            phone = phone,
            eyeColor = eyeColor,
            registered = registered,
            about = about,
            address = address,
            company = company,
            email = email,
            favoriteFruit = favoriteFruit,
            friends = friends.map { friend -> friend.id },
            gender = gender,
            latitude = latitude,
            longitude = longitude,
            tags = tags
        )
    }
