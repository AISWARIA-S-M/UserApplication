package com.ibm.usersapplication.app

import android.app.Application

class UserDetailsInfo : Application() {
    companion object {
        lateinit var mInstance: UserDetailsInfo
    }
    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}