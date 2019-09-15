package com.example.shopbacktest.data.other

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import android.util.Log
import com.example.shopbacktest.data.model.api.UserResponse
import com.example.shopbacktest.repository.UsersRepo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class UserDataSource(val repo: UsersRepo,
                     val compositeDisposable: CompositeDisposable,
                     val pageSize: Int
                     ): ItemKeyedDataSource<Int, UserResponse>() {
    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()
    private var retryCompletable: Completable? = null

    fun retry(){
        if (retryCompletable != null){
            compositeDisposable.add(retryCompletable!!.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe({},{
                        it.printStackTrace()
                    }))
        }

    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<UserResponse>) {
        // update network states.
        // we also provide an initial load state to the listeners so that the UI can know when the
        // very first list is loaded
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        //load adapter from the api
        compositeDisposable.add(repo.getUserList(pageSize, 0).subscribe({
            Log.i("UserDataSource", "loadInitial list data response size  ="+ it.size)
            initSuccess()
            callback.onResult(it)
        },{
            it.printStackTrace()
//            setRetry(Action {loadInitial(params, callback)}) //cannot del. it will bug in case init fail
            val error = NetworkState.error(if (it is UnknownHostException) "No internet connection" else it.message)
            networkState.postValue(error)
            initialLoad.postValue(error)

        }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<UserResponse>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(repo.getUserList( pageSize, params.key).subscribe({
            //clear retry since last request succeeded
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(it)
        },{
            val error = NetworkState.error(if (it is UnknownHostException) "No internet connection" else it.message)
            networkState.postValue(error)
            setRetry(Action { loadAfter(params, callback) })
        }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<UserResponse>) {
    }

    override fun getKey(item: UserResponse): Int {
        return item.id
    }

    private fun initSuccess(){
        networkState.postValue(NetworkState.LOADED)
        initialLoad.postValue(NetworkState.LOADED)
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}