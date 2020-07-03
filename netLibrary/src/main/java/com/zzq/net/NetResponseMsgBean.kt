package com.zzq.net

import androidx.annotation.StringRes

data class NetResponseMsgBean(
    val msgCode: Int,
    val msg: String? = null,
    @StringRes val msgResId: Int? = null
)