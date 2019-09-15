package com.example.shopbacktest.data.model.api

import com.google.gson.annotations.SerializedName

data class UserDetailResponse (
        @SerializedName("avatar_url")
        val avatarUrl: String,
        val login: String,
        val name: String,
        val bio: String,
        val location: String,
        val blog: String,
        @SerializedName("site_admin")
        val badge: Boolean
){

}