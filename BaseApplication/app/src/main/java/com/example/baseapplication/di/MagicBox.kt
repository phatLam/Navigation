package com.example.baseapplication.di

import com.example.baseapplication.MainActivity
import dagger.Component

@Component
interface MagicBox {
    fun poke(app: MainActivity)
}