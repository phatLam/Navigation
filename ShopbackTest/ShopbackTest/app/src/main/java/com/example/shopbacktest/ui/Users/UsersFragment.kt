package com.example.shopbacktest.ui.Users

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

import com.example.shopbacktest.R
import com.example.shopbacktest.base.fragment.BaseFragment
import com.example.shopbacktest.data.other.NetworkState
import com.example.shopbacktest.data.other.Status
import com.example.shopbacktest.ui.Users.adapter.NetworkStateListener
import com.example.shopbacktest.ui.Users.adapter.UserAdapter
import com.example.shopbacktest.ui.Users.adapter.UserListener
import kotlinx.android.synthetic.main.users_fragment.*

class UsersFragment : BaseFragment(), NetworkStateListener, UserListener {

    override fun retryCallback() {
        viewModel.retry()
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun userItemListener(login: String) {
        val bundle = Bundle()
        bundle.putString(LOGIN, login)
        findNavController().navigate(R.id.detailUserFragment, bundle)
    }

    override fun getLayoutId(): Int = R.layout.users_fragment

    companion object {
        val LOGIN = "LOGIN"
    }

    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: UsersViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel::class.java)
        viewModel.userList.observe(viewLifecycleOwner, Observer { if (it!!.size > 0) adapter.submitList(it) })
        viewModel.getNetworkState().observe(viewLifecycleOwner, Observer { adapter.setNetworkState(it) })
        viewModel.getRefreshState().observe(viewLifecycleOwner, Observer {
            if (adapter.currentList != null && adapter.currentList!!.size >0) {
                layout_refresh.isRefreshing = it?.status == NetworkState.LOADING.status
                setInitialLoadingState(it, true)
            }
            else{
                setInitialLoadingState(it, false)
            }

        })
        initAdapter()
        btn_retry.setOnClickListener { viewModel.refresh() }
        layout_refresh.setOnRefreshListener { viewModel.refresh() }
    }

    private fun initAdapter(){
        val llManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = UserAdapter(this, this)
        rv_users.layoutManager = llManager
        rv_users.adapter = adapter

    }

    private fun setInitialLoadingState(networkState: NetworkState?, hasUser: Boolean) {
        //error message
        if (networkState?.message != null) {
            if (hasUser)
                showError(networkState.message)
            else{
                txt_error.text = networkState.message
            }
        }
        loadingProgressBar.visibility = if(networkState?.status == Status.RUNNING && !hasUser) View.VISIBLE else View.GONE
        group_empty.visibility = if (networkState?.message != null && !hasUser) View.VISIBLE else View.GONE
        layout_refresh.isEnabled = hasUser || networkState?.status == Status.SUCCESS
    }
}
