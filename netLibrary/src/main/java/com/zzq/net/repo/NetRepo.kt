package com.zzq.net.repo

import androidx.lifecycle.MutableLiveData
import com.zzq.common.base.BaseRepo
import com.zzq.net.NetResponseMsgBean
import com.zzq.net.callback.NetCallback
import com.zzq.net.callback.OnNetRequestListener
import retrofit2.Call
import retrofit2.Response

open class NetRepo(private val errorLiveData: MutableLiveData<NetResponseMsgBean>) : BaseRepo() {

    fun <T> enqueue(call: Call<T>, listener: OnNetRequestListener<T>) {
        call.enqueue(object : NetCallback<T>(errorLiveData) {
            override fun onSuccess(call: Call<T>, response: Response<T>) {
                listener.onResponse(response.body()!!)
            }
        })
    }

    fun <T> enqueue(call: Call<T>, liveData: MutableLiveData<T>) {
        call.enqueue(object : NetCallback<T>(errorLiveData) {
            override fun onSuccess(call: Call<T>, response: Response<T>) {
                liveData.value = response.body()
            }
        })
    }
}