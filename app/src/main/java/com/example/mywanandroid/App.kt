package com.example.mywanandroid

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class App : Application() {
    companion object {
        private lateinit var Instance: App

        fun getInstance() = Instance
    }

    override fun onCreate() {
        super.onCreate()

        Instance = this

        //初始化 orhanobut:logger
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}