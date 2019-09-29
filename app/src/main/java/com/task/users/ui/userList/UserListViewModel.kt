package com.task.users.ui.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.users.repository.UserRepository
import com.task.users.ui.adapter.UserDataItem
import com.task.users.ui.base.BaseViewModel
import com.task.users.utils.viewModel.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    init {
        disposables.add(userRepository.getUsers()
            .map { it.map { user -> UserDataItem.UserItem(user) } }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingData.setValue(true) }
            .doAfterTerminate { loadingData.setValue(false) }
            .subscribe(
                {
                    users.value = it
                },
                { errorData.value = it }
            )
        )
    }

    private val openUserDetailsEvent = SingleLiveEvent<Int>()
    val openUserDetails: SingleLiveEvent<Int> = openUserDetailsEvent

    private var users = MutableLiveData<List<UserDataItem.UserItem>>()

    fun getUsers(): LiveData<List<UserDataItem.UserItem>> = users

    fun userItemClicked(id: Int) {
        openUserDetailsEvent.value = id
    }

    fun loadUsers() = userRepository.remoteLoadUsers()
        .map { it.map { user -> UserDataItem.UserItem(user) } }
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { loadingData.setValue(true) }
        .doAfterTerminate { loadingData.setValue(false) }
        .subscribe(
            { users.value = it },
            { errorData.value = it }
        )
}
