package com.zzq.life.picture.vm

import androidx.lifecycle.MutableLiveData
import com.zzq.life.picture.interfaces.Operation
import com.zzq.life.picture.net.NetManager
import com.zzq.life.picture.net.response.GankPictureData
import com.zzq.net.NetResponseMsgBean
import com.zzq.net.repo.NetRepo

class PictureRepo(errorLiveData: MutableLiveData<NetResponseMsgBean>) : NetRepo(errorLiveData),
    Operation.Repo {

    private val gankApi = NetManager.getGankApi()

    override fun getGankPicture(
        pageIndex: Int,
        pageSize: Int,
        liveData: MutableLiveData<GankPictureData>
    ) {
        enqueue(gankApi.getGankPicture(pageIndex, pageSize), liveData)
    }
}