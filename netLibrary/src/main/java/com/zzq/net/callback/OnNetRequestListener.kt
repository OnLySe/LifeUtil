package com.zzq.net.callback

interface OnNetRequestListener<T> {
    fun onResponse(entity: T)

}