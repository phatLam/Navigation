package com.example.shopbacktest.di

import com.example.shopbacktest.ui.Users.UsersFragment
import com.example.shopbacktest.ui.userDetail.DetailUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeDetailUserFragment(): DetailUserFragment

    @ContributesAndroidInjector
    abstract fun contributeUsersFragment(): UsersFragment

}