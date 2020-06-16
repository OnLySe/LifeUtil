package com.zzq.base.base

import android.app.Application
import android.content.Context
import com.zzq.base.util.ToastUtil

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ToastUtil.app = this
        instance = this
    }

    companion object {
        lateinit var instance: BaseApplication
//        var instance:BaseApplication? = null
//            private set
    }
}