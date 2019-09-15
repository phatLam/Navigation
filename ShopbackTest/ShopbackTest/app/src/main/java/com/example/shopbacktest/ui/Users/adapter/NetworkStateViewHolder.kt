package com.example.shopbacktest.ui.Users.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbacktest.R
import com.example.shopbacktest.data.other.NetworkState
import com.example.shopbacktest.data.other.Status
import kotlinx.android.synthetic.main.item_network_state.view.*

class NetworkStateViewHolder(val view: View, val listener: NetworkStateListener):
        RecyclerView.ViewHolder(view) {

    fun bindTo(networkState: NetworkState?){
        itemView.errorMessageTextView.visibility = View.GONE
        if (networkState?.message != null) {
            listener.showError(networkState.message)
        }

        //loading and retry
        itemView.retryLoadingButton.setOnClickListener {
            Log.i("NetworkStateViewHolder","retryLoadingButton listener")
            listener.retryCallback()
        }
        itemView.retryLoadingButton.visibility = if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.loadingProgressBar.visibility = if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE

    }

    companion object {
        @JvmStatic
        fun create(parent: ViewGroup, listener: NetworkStateListener): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            return NetworkStateViewHolder(view, listener)
        }
    }
}

interface NetworkStateListener{
    fun retryCallback()
    fun showError(message: String)
}