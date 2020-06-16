package com.zzq.base.base

import androidx.lifecycle.MutableLiveData
import com.zzq.base.bean.ResponseMsgBean

open class BaseRepo {

    /**
     * 由于通用，需要注意手动移除observer！
     */
    protected lateinit var responseMsgLiveData: MutableLiveData<ResponseMsgBean>

    open fun obtainResponseMsgLiveData(liveData: MutableLiveData<ResponseMsgBean>) {
        responseMsgLiveData = liveData
    }
}