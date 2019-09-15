package com.example.shopbacktest.di

import com.example.shopbacktest.BuildConfig
import com.example.shopbacktest.data.remote.ServerApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideServerAPI(): ServerApi {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val interceptor = Interceptor {
            val builder = it.request().newBuilder().
                    addHeader("Authorization", "Bearer de7c52ff393f8dfad7ee2d828529ff31dc42dafe")
            val request = builder.build()
            it.proceed(request)
        }
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(interceptor).addInterceptor(logInterceptor)
        val timeout = 30
        okBuilder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
        okBuilder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(okBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ServerApi::class.java)
    }


}