package com.example.hw15_2_2.ui

import android.app.Application
import android.content.Context
import android.util.Log
import kotlin.concurrent.thread

class GlobalApplication : Application() {

    companion object{
        private var appContext : Context? = null
        fun getAppContext() : Context{
            return appContext!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}