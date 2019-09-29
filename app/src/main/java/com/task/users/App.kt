package com.task.users

import android.app.Application
import com.task.users.di.AppComponent
import com.task.users.di.DaggerAppComponent

class App: Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}
