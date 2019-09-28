package com.task.users.di.module

import android.content.Context
import com.task.users.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): Context = app
}
