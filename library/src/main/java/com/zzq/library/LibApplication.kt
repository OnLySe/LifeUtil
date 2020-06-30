package com.zzq.library

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

open class LibApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.is_debug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}