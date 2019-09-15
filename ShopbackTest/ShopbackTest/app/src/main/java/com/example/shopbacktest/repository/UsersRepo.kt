package com.example.shopbacktest.repository

import com.example.shopbacktest.data.model.api.UserDetailResponse
import com.example.shopbacktest.data.remote.ServerApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepo @Inject constructor(private val api: ServerApi){
    fun getUserList( pageSize: Int, since: Int) = api.getUsers( pageSize, since)

    fun getSingleUser(login: String) = api.getSingleUser(login)
}