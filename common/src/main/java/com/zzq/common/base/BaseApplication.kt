package com.zzq.common.base

import android.app.Application
import com.zzq.common.util.ToastUtil

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