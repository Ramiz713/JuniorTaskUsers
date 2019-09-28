package com.task.users.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.task.users.R
import com.task.users.entities.EyeColor
import com.task.users.entities.FavoriteFruit
import com.task.users.entities.User
import com.task.users.utils.formatDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_header.*
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.android.synthetic.main.item_user_details.*

sealed class UserDataViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    class HeaderViewHolder(override val containerView: View) : UserDataViewHolder(containerView) {

        companion object {
            fun from(parent: ViewGroup): HeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_header, parent, false)
                return HeaderViewHolder(view)
            }
        }

        fun bind(header: String) {
            textViewHeader.text = header
        }
    }

    class UserItemViewHolder(override val containerView: View) : UserDataViewHolder(containerView) {

        companion object {
            fun from(parent: ViewGroup): UserItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_user, parent, false)
                return UserItemViewHolder(view)
            }
        }

        fun bind(user: User) =
            with(user) {
                textViewUserName.text = name
                textViewUserEmail.text = email
                containerView.context.let {
                    textViewIsActive.text =
                        if (isActive) it.getString(R.string.user_active_status)
                        else it.getString(R.string.user_non_active_status)
                    textViewIsActive.setTextColor(
                        if (isActive) ContextCompat.getColor(it, R.color.colorActive)
                        else ContextCompat.getColor(it, R.color.colorNonActive)
                    )
                }
                linearContainer.run {
                    alpha = if (isActive) 1f else 0.5f
                    isEnabled = isActive
                }
            }
    }

    class UserDetailsViewHolder(override val containerView: View) :
        UserDataViewHolder(containerView) {

        companion object {
            fun from(parent: ViewGroup): UserDetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_user_details, parent, false)
                return UserDetailsViewHolder(view)
            }
        }

        fun bind(user: User) =
            with(containerView.context) {
                textViewName.text = user.name
                textViewEmail.text = getString(R.string.user_email, user.email)
                textViewCompany.text = getString(R.string.user_company, user.company)
                textViewAge.text = getString(R.string.user_age, user.age)
                textViewPhone.text = getString(R.string.user_phone, user.phone)
                textViewAddress.text = getString(R.string.user_address, user.address)
                textViewAbout.text = getString(R.string.user_about, user.about)
                textViewRegistered.text =
                    getString(R.string.user_registered, formatDate(user.registered))
                textViewCoordinates.text =
                    getString(
                        R.string.user_coordinates,
                        user.latitude.toString(),
                        user.longitude.toString()
                    )

                setEyeColorDrawable(user.eyeColor)
                setFavoriteFruit(user.favoriteFruit)
                initClickListeners(this, user)
            }

        private fun setFavoriteFruit(favoriteFruit: FavoriteFruit) {
            val drawable = when (favoriteFruit) {
                FavoriteFruit.APPLE -> R.drawable.ic_apple
                FavoriteFruit.BANANA -> R.drawable.ic_banana
                FavoriteFruit.STRAWBERRY -> R.drawable.ic_strawberry
            }
            textViewFavoriteFruit.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
        }

        private fun setEyeColorDrawable(eyeColor: EyeColor) {
            val drawable = when (eyeColor) {
                EyeColor.BROWN -> R.drawable.ic_dot_brown_24dp
                EyeColor.GREEN -> R.drawable.ic_dot_green_24dp
                EyeColor.BLUE -> R.drawable.ic_dot_blue_24dp
            }
            textViewEyeColor.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
        }

        private fun initClickListeners(context: Context, user: User) =
            with(context) {
                textViewEmail.setOnClickListener {
                    val emailIntent = Intent(Intent.ACTION_SENDTO)
                    emailIntent.data = Uri.parse("mailto:${user.email}")
                    if (emailIntent.resolveActivity(packageManager) != null)
                        startActivity(emailIntent)
                }
                textViewPhone.setOnClickListener {
                    val phoneIntent = Intent(Intent.ACTION_DIAL)
                    phoneIntent.data = Uri.parse("tel:${user.phone}")
                    if (phoneIntent.resolveActivity(packageManager) != null)
                        startActivity(phoneIntent)
                }
                textViewCoordinates.setOnClickListener {
                    val geoIntent = Intent(Intent.ACTION_VIEW)
                    geoIntent.data = Uri.parse("geo:${user.latitude},${user.longitude}")
                    if (geoIntent.resolveActivity(packageManager) != null)
                        startActivity(geoIntent)
                }
            }
    }
}
