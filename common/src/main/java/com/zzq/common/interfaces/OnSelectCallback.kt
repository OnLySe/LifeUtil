package com.zzq.common.interfaces

abstract class OnSelectCallback {
    abstract fun onPositive(msg: String?)
    fun onNegative() {}
}