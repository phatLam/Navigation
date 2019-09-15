package com.example.baseapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.baseapplication.di.DaggerMagicBox
import com.example.baseapplication.model.Info
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var info: Info
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMagicBox.create().poke(this)
        info.text
    }
}
