package com.example.shopbacktest.ui.Users.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbacktest.R
import com.example.shopbacktest.data.model.api.UserResponse
import com.example.shopbacktest.util.fetchCircleImage
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(val view: View, val listener: UserListener):RecyclerView.ViewHolder(view) {

    fun bindTo(user: UserResponse?){
        user?.let {
            view.setOnClickListener{ listener.userItemListener(user.login)}
            view.txt_login.text = user.login
            view.txt_badge.visibility = if (user.badge) View.VISIBLE else View.GONE
            fetchCircleImage(view.context, user.avatarUrl, view.img_avatar) }

    }
    companion object{
        @JvmStatic
        fun create(parent: ViewGroup, listener: UserListener): UserViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view, listener)
        }
    }
}

interface UserListener{
    fun userItemListener(login: String)
}