package com.task.users.di

import com.task.users.di.module.*
import com.task.users.ui.MainActivity
import com.task.users.ui.userDetails.UserDetailsFragment
import com.task.users.ui.userList.UserListFragment
import dagger.Component
import javax.inject.Singleton
import com.task.users.App
import android.app.Application
import dagger.Binds
import dagger.BindsInstance


@Singleton
@Component(
    modules = [
        RemoteModule::class,
        RoomModule::class,
        ViewModelModule::class,
        ApiServiceModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(fragment: UserListFragment)

    fun inject(fragment: UserDetailsFragment)
}
