package com.zzq.life.picture.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zzq.common.base.BaseViewModel
import com.zzq.life.picture.interfaces.Operation
import com.zzq.life.picture.net.response.GankPictureData
import com.zzq.net.NetResponseMsgBean

class PictureViewModel : BaseViewModel<PictureRepo>(), Operation.ViewModel {
    private lateinit var errorLiveData: MutableLiveData<NetResponseMsgBean>
    private lateinit var gankPictureLiveData: MutableLiveData<GankPictureData>

    override fun initialRepo(): PictureRepo {
        errorLiveData = MutableLiveData()
        gankPictureLiveData = MutableLiveData()
        return PictureRepo(errorLiveData)
    }

    fun getGankPictureLiveData(): LiveData<GankPictureData> {
        return gankPictureLiveData
    }

    override fun getGankPicture(pageIndex: Int, pageSize: Int) {
        repo.getGankPicture(pageIndex, pageSize, gankPictureLiveData)
    }
}