package com.task.users.api

import com.task.users.api.apiEntities.ApiUser
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface UsersApiService {

    @GET("s/s8g63b149tnbg8x/users.json?dl=1")
    fun getUsers(): Single<List<ApiUser>>
}
