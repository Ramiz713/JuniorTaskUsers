package com.task.users.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.task.users.utils.ITEM_VIEW_ITEM_DETAILS
import com.task.users.utils.ITEM_VIEW_TYPE_HEADER
import com.task.users.utils.ITEM_VIEW_TYPE_ITEM

class UserAdapter(private val userItemClickListener: (Int) -> Unit) :
    ListAdapter<UserDataItem, UserDataViewHolder>(DIFF_CALLBACK) {

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is UserDataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is UserDataItem.UserItem -> ITEM_VIEW_TYPE_ITEM
            is UserDataItem.UserDetailsItem -> ITEM_VIEW_ITEM_DETAILS
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder =
        when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> UserDataViewHolder.HeaderViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> UserDataViewHolder.UserItemViewHolder.from(parent)
            ITEM_VIEW_ITEM_DETAILS -> UserDataViewHolder.UserDetailsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) =
        when (holder) {
            is UserDataViewHolder.HeaderViewHolder -> {
                val item = getItem(position) as UserDataItem.Header
                holder.bind(item.header)
            }
            is UserDataViewHolder.UserItemViewHolder -> {
                val user = (getItem(position) as UserDataItem.UserItem).user
                holder.bind(user)
                holder.itemView.setOnClickListener { userItemClickListener(user.id) }

            }
            is UserDataViewHolder.UserDetailsViewHolder -> {
                val item = getItem(position) as UserDataItem.UserDetailsItem
                holder.bind(item.user)
            }
        }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserDataItem>() {

            override fun areItemsTheSame(
                oldItem: UserDataItem,
                newItem: UserDataItem
            ): Boolean = oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: UserDataItem,
                newItem: UserDataItem
            ): Boolean = oldItem == newItem
        }
    }
}
