package com.example.shopbacktest.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbacktest.di.Injectable
import com.example.shopbacktest.di.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment: Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }


}