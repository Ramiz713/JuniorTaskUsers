package com.task.users.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.users.ui.userDetails.UserDetailsViewModel
import com.task.users.ui.userList.UserListViewModel
import com.task.users.utils.viewModel.ViewModelFactory
import com.task.users.utils.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    internal abstract fun userListViewModel(viewModel: UserListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    internal abstract fun userDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel
}
