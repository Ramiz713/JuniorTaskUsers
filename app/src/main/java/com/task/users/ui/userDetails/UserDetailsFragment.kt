package com.task.users.ui.userDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.task.users.App
import com.task.users.R
import com.task.users.ui.adapter.UserAdapter
import com.task.users.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserDetailsFragment : BaseFragment() {

    @Inject
    override lateinit var viewModel: UserDetailsViewModel

    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
        viewModel.getUserDetails(args.Id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_details, container, false)

    override fun initObservers() {
        initRecycler()
        observeUsers()
        observeOpenUserDetails()
    }

    private fun observeUsers() =
        viewModel.getUserDataItems().observe(this, Observer {
            (recyclerViewUsers.adapter as UserAdapter).submitList(it)
        })

    private fun observeOpenUserDetails() =
        viewModel.openUserFriendDetails.observe(this, Observer {
            it?.let {
                val action = UserDetailsFragmentDirections.actionUserDetailsFragmentSelf(it)
                findNavController().navigate(action)
            }
        })

    private fun initRecycler() {
        recyclerViewUsers.adapter = UserAdapter { id: Int -> viewModel.userFriendClicked(id) }
        recyclerViewUsers.itemAnimator = null
    }
}
