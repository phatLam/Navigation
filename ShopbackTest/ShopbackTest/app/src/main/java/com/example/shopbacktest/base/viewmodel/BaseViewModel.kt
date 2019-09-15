package com.example.shopbacktest.base.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class BaseViewModel: ViewModel() {
    val disposable = CompositeDisposable()
    private val error = MutableLiveData<String>()
    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
    open fun setError(error : String){
        this.error.postValue(error)
    }

    open fun getError(): LiveData<String> {
        return error
    }
}