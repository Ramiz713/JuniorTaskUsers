package com.task.users.ui.userList


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.task.users.App
import com.task.users.R
import com.task.users.ui.adapter.UserAdapter
import com.task.users.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_user_list.*
import javax.inject.Inject

class UserListFragment : BaseFragment() {

    @Inject
    override lateinit var viewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_user_list, container, false)

    override fun initObservers() {
        observeLoading(swipeRefresh)
        observeUsers()
        observeOpenUserDetails()
        initRecycler()
        swipeRefresh.setOnRefreshListener { viewModel.loadUsers() }
    }

    private fun observeLoading(swipeRefresh: SwipeRefreshLayout) =
        viewModel.isLoading().observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = it
        })

    private fun observeUsers() =
        viewModel.getUsers().observe(this, Observer {
            (recyclerViewUsers.adapter as UserAdapter).submitList(it)
        })

    private fun observeOpenUserDetails() =
        viewModel.openUserDetails.observe(this, Observer {
            it?.let {
                val action = UserListFragmentDirections.actionUserListFragmentToUserDetailsFragment(it)
                findNavController().navigate(action)
            }
        })

    private fun initRecycler() {
        recyclerViewUsers.adapter = UserAdapter { id: Int -> viewModel.userItemClicked(id) }
        recyclerViewUsers.itemAnimator = null
    }
}
