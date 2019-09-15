package com.example.shopbacktest.di

import android.app.Application
import com.example.shopbacktest.ShopbackTestAppliacation
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        BaseActivityModule::class,
            AppModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : Application):Builder

        fun build():AppComponent
    }

    fun inject(application: ShopbackTestAppliacation)
}