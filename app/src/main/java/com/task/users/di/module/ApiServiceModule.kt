package com.task.users.di.module

import com.task.users.api.UsersApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApiService(retrofit: Retrofit): UsersApiService =
        retrofit.create(UsersApiService::class.java)
}
