package com.example.shopbacktest.data.other

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.shopbacktest.data.model.api.UserResponse
import com.example.shopbacktest.repository.UsersRepo
import io.reactivex.disposables.CompositeDisposable

class UserDataSourceFactory(val repo: UsersRepo,
                            val compositeDisposable: CompositeDisposable,
                            val pageSize: Int): DataSource.Factory<Int, UserResponse>() {
    val userDataSourceLiveData = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, UserResponse> {
        val userDataSource = UserDataSource(repo, compositeDisposable, pageSize)
        userDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }

}