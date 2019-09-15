package com.example.shopbacktest.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.shopbacktest.ui.Users.UsersViewModel
import com.example.shopbacktest.ui.userDetail.DetailUserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    abstract fun bindUsersViewModel(usersViewModel : UsersViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailUserViewModel::class)
    abstract fun bindDetailUserViewModel(detailUserViewModel: DetailUserViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}