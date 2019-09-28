package com.task.users.utils

import java.text.SimpleDateFormat


fun formatDate(date:String): String {
    var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z")
    val newDate = format.parse(date)

    format = SimpleDateFormat("HH:mm dd.MM.yy")
    return format.format(newDate)
}
