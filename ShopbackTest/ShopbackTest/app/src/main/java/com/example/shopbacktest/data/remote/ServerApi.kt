package com.example.shopbacktest.data.remote

import com.example.shopbacktest.data.model.api.UserResponse
import com.example.shopbacktest.data.model.api.UserDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {
    @GET("users/{login}")
    fun getSingleUser(@Path("login") login: String): Single<UserDetailResponse>

    @GET("users")
    fun getUsers(@Query("per_page") pageSize: Int,
                 @Query("since") since: Int): Single<List<UserResponse>>

}