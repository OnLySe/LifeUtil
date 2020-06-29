package com.zzq.library

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

open class LibApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }
}