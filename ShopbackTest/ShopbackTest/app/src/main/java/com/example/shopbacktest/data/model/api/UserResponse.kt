package com.example.shopbacktest.data.model.api

import com.google.gson.annotations.SerializedName

data class UserResponse(
        val id: Int,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        val login: String,
        @SerializedName("site_admin")
        val badge: Boolean
)