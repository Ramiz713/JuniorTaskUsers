package com.task.users.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModel: BaseViewModel

    protected abstract fun initObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeError(view)
        initObservers()
    }

    private fun observeError(view: View) =
        viewModel.error().observe(viewLifecycleOwner, Observer {
            Snackbar.make(view, it?.message ?: "error", Snackbar.LENGTH_SHORT).show()
        })
}
