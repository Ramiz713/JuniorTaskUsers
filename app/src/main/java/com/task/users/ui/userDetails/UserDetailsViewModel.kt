package com.task.users.ui.userDetails

import androidx.lifecycle.MutableLiveData
import com.task.users.repository.UserRepository
import com.task.users.ui.adapter.UserDataItem
import com.task.users.ui.base.BaseViewModel
import com.task.users.utils.viewModel.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val openUserFriendDetailsEvent = SingleLiveEvent<Int>()
    val openUserFriendDetails: SingleLiveEvent<Int> = openUserFriendDetailsEvent

    private lateinit var userDetails: UserDataItem.UserDetailsItem
    private var userDataItems = MutableLiveData<List<UserDataItem>>()

    fun getUserDataItems() = userDataItems

    fun userFriendClicked(id: Int) {
        openUserFriendDetailsEvent.value = id
    }

    fun getUserDetails(id: Int) =
        disposables.add(
            userRepository.getUser(id)
                .doOnSubscribe { disposables.add(it) }
                .map { UserDataItem.UserDetailsItem(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        userDetails = it
                        userDataItems.value = listOf(it)
                        getUserFriends(it.user.friends)
                    },
                    { errorData.value = it }
                )
        )

    private fun getUserFriends(friends: List<Int>) =
        disposables.add(
            userRepository.getUserFriends(friends)
                .map { it.map { user -> UserDataItem.UserItem(user) } }
                .map { listOf(userDetails) + listOf(UserDataItem.Header("Friends:")) + it }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { userDataItems.value = it },
                    { errorData.value = it }
                ))
}
