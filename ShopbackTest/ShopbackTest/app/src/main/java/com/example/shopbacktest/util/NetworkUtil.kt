package com.example.shopbacktest.util

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil {
    companion object {
        @JvmStatic
        fun checkNetwork(context: Context): Boolean{
            val cManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val nInfo = cManager.activeNetworkInfo
            return nInfo != null && nInfo.isConnected
        }
    }
}