package com.example.shopbacktest.ui.userDetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.shopbacktest.base.viewmodel.BaseViewModel
import com.example.shopbacktest.data.model.api.UserDetailResponse
import com.example.shopbacktest.repository.UsersRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class DetailUserViewModel @Inject constructor( private val repo: UsersRepo) : BaseViewModel() {

    private val result=  MutableLiveData<UserDetailResponse>()
    fun getResult(): LiveData<UserDetailResponse> = result

    fun getUser(login: String){
        disposable.add(repo.getSingleUser(login).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe({
                    it?.let { userResponse ->
                        Log.d("DetailUserViewModel", "get detail successfully")
                        result.value = userResponse
                    }
                },{
                    if (it is HttpException){
                        setError(it.message())
                    }
                    if (it is UnknownHostException){
                        setError("No internet connection")
                    }
                })
        )

    }

}
