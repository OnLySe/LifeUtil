package com.zzq.base.base

import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    /*protected lateinit var mImmersionBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.init()
        initStatusBar()
    }

    protected open fun initStatusBar() {
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            mImmersionBar.statusBarDarkFont(true)
        }
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.white).init()
    }

    *//**
     * 设置Toolbar返回键关闭Activity
     * 注意：getSupportActionBar操作需要放在调用本方法之后，如setTitle()
     *
     * @param toolbar
     *//*
    protected open fun setToolbarReturnFun(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        val ab = supportActionBar
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true)
            ab.setDisplayShowTitleEnabled(false)
            toolbar.setNavigationOnClickListener { returnAction() }
        }
    }

    protected open fun returnAction() {
        finish()
    }*/
}