package com.task.users.di.module

import com.task.users.api.UsersApiService
import com.task.users.repository.UserRepository
import com.task.users.repository.UserRepositoryImpl
import com.task.users.repository.database.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(usersApiService: UsersApiService, userDao: UserDao): UserRepository =
        UserRepositoryImpl(usersApiService, userDao)
}
