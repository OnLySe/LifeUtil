package com.zzq.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zzq.common.bean.ResponseMsgBean

abstract class BaseViewModel<T : BaseRepo> : ViewModel() {

    /**
     * 可通用返回各类自定义消息
     */
    protected var responseMsgLiveData = MutableLiveData<ResponseMsgBean>()
        protected set

    protected var repo: T = initialRepo().apply { obtainResponseMsgLiveData(responseMsgLiveData) }

    abstract fun initialRepo(): T

    fun getResponseMsgLiveData(): LiveData<ResponseMsgBean> {
        return responseMsgLiveData
    }

}