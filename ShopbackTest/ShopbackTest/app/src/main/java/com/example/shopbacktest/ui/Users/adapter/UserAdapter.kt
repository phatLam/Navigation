package com.example.shopbacktest.ui.Users.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.shopbacktest.R
import com.example.shopbacktest.data.model.api.UserResponse
import com.example.shopbacktest.data.other.NetworkState

class UserAdapter(private val networkListener: NetworkStateListener,
                  private val userListener: UserListener): PagedListAdapter<UserResponse, RecyclerView.ViewHolder>(UserDiffCallback) {
    private var networkState: NetworkState? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when( viewType){
            R.layout.item_user ->{UserViewHolder.create(parent,userListener)}
            R.layout.item_network_state ->{NetworkStateViewHolder.create(parent, networkListener)}
            else -> throw IllegalArgumentException("unknow view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            R.layout.item_user ->{(holder as UserViewHolder).bindTo(getItem(position))}
            R.layout.item_network_state ->{(holder as NetworkStateViewHolder).bindTo(networkState)}
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else{
            R.layout.item_user
        }

    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    companion object{
        val UserDiffCallback = object : DiffUtil.ItemCallback<UserResponse>(){
            override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem.login == newItem.login
            }

            override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
               return oldItem == newItem
            }

        }
    }
}