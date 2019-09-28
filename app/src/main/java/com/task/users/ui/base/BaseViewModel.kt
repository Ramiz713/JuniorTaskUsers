package com.task.users.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.users.utils.viewModel.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()
    protected val loadingData = MutableLiveData<Boolean>()
    protected var errorData = SingleLiveEvent<Throwable>()

    fun isLoading(): LiveData<Boolean> = loadingData

    fun error(): SingleLiveEvent<Throwable> = errorData

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
