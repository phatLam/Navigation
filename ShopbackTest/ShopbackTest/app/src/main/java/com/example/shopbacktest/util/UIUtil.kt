package com.example.shopbacktest.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun fetchCircleImage(context: Context, url: String?, imageView: ImageView){
    Glide.with(context).load(url).apply(RequestOptions().circleCrop()).into(imageView)
}

fun fetchImage(context: Context, url: String?, imageView: ImageView){
    Glide.with(context).load(url).into(imageView)
}