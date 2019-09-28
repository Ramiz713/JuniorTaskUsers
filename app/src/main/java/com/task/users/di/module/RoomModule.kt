package com.task.users.di.module

import android.app.Application
import androidx.room.Room
import com.task.users.repository.database.AppDatabase
import com.task.users.repository.database.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    companion object {
        private const val DATABASE_NAME = "users_app.db"
    }
}
