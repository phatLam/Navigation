package com.example.shopbacktest.ui.Users

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.shopbacktest.base.viewmodel.BaseViewModel
import com.example.shopbacktest.data.model.api.UserResponse
import com.example.shopbacktest.data.other.NetworkState
import com.example.shopbacktest.data.other.UserDataSource
import com.example.shopbacktest.data.other.UserDataSourceFactory
import com.example.shopbacktest.repository.UsersRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val repo: UsersRepo) : BaseViewModel() {
    val userList: LiveData<PagedList<UserResponse>>
    private val pageSize = 20
    private val sourceFactory: UserDataSourceFactory

    init{
        sourceFactory = UserDataSourceFactory(repo, disposable, pageSize)
        val config = PagedList.Config.Builder().
                setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize *2)
                .setEnablePlaceholders(false)
                .build()
        userList = LivePagedListBuilder<Int, UserResponse>(sourceFactory, config).build()
    }

    fun retry() {
        sourceFactory.userDataSourceLiveData.value!!.retry()
    }

    fun refresh() {
        sourceFactory.userDataSourceLiveData.value!!.invalidate()
    }

    fun getNetworkState(): LiveData<NetworkState> = Transformations.switchMap<UserDataSource, NetworkState>(
            sourceFactory.userDataSourceLiveData, { it.networkState })

    fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<UserDataSource, NetworkState>(
            sourceFactory.userDataSourceLiveData, { it.initialLoad })

}
