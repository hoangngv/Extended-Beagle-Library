package com.vt.extendedbeaglelib.base

import android.app.Application

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
        BeagleSetup().init(instance)
    }

    companion object {
        lateinit var instance: Application
    }
}